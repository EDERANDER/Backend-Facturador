package com.sistemas.facturador.models;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sistemas.facturador.models.numeracion.NumeracionComprobante;
import com.sistemas.facturador.services.NumeracionService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class InvoiceService {

    @Value("${factiliza.token}")
    private String factilizaToken;

    private final NumeracionService numeracionService;

    public InvoiceService(NumeracionService numeracionService) {
        this.numeracionService = numeracionService;
    }

    public String sendToFactiliza(InvoiceRequest invoiceRequest) throws Exception {

        NumeracionComprobante num = numeracionService.obtenerNumeracion(invoiceRequest.getTipo_Doc(), invoiceRequest);
        invoiceRequest.setSerie(num.getSerie());
        invoiceRequest.setCorrelativo(num.getCorrelativo());

        // 🕒 Formatear fechas de forma de pago
        if (invoiceRequest.getForma_pago() != null) {
            for (InvoiceRequest.FormaPago fp : invoiceRequest.getForma_pago()) {
                if (fp.getFecha_Pago() != null) {
                    try {
                        OffsetDateTime odt = OffsetDateTime.parse(fp.getFecha_Pago());
                        fp.setFecha_Pago(odt.toLocalDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Formato inválido en fecha_Pago: " + fp.getFecha_Pago());
                    }
                }
            }
        }

        // 📨 Convertir a JSON y enviar a Factiliza
        ObjectMapper mapper = new ObjectMapper();
        String jsonBody = mapper.writeValueAsString(invoiceRequest);

        HttpResponse<String> response = Unirest.post("https://apife-qa.factiliza.com/api/v1/invoice/send")
                .header("Authorization", "Bearer " + factilizaToken)
                .header("Content-Type", "application/json")
                .body(jsonBody)
                .asString();

        return response.getBody();
    }
}
