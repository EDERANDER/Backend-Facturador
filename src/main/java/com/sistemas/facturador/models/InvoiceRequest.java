package com.sistemas.facturador.models;
import java.security.PublicKey;
import java.util.List;


public class InvoiceRequest {
    public String tipo_Operacion;
    public String tipo_Doc;
    public String serie;
    public String correlativo;
    public String tipo_Moneda;
    public String fecha_Emision;
    public String empresa_Ruc;
    public String cliente_Tipo_Doc;
    public String cliente_Num_Doc;
    public String cliente_Razon_Social;
    public String cliente_Direccion;
    public double monto_Oper_Gravadas;
    public double monto_Igv;
    public double total_Impuestos;
    public double valor_Venta;
    public double sub_Total;
    public double monto_Imp_Venta;
    public double monto_Oper_Exoneradas;
    public String estado_Documento;
    public boolean manual;
    public String id_Base_Dato;
    public List<Detalle> detalle;
    public List<FormaPago> forma_pago;
    public List<Legend> legend;

    public InvoiceRequest (){}

    public String getTipo_Operacion() {
        return tipo_Operacion;
    }

    public void setTipo_Operacion(String tipo_Operacion) {
        this.tipo_Operacion = tipo_Operacion;
    }

    public String getTipo_Doc() {
        return tipo_Doc;
    }

    public void setTipo_Doc(String tipo_Doc) {
        this.tipo_Doc = tipo_Doc;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(String correlativo) {
        this.correlativo = correlativo;
    }

    public String getTipo_Moneda() {
        return tipo_Moneda;
    }

    public void setTipo_Moneda(String tipo_Moneda) {
        this.tipo_Moneda = tipo_Moneda;
    }

    public String getFecha_Emision() {
        return fecha_Emision;
    }

    public void setFecha_Emision(String fecha_Emision) {
        this.fecha_Emision = fecha_Emision;
    }

    public String getEmpresa_Ruc() {
        return empresa_Ruc;
    }

    public void setEmpresa_Ruc(String empresa_Ruc) {
        this.empresa_Ruc = empresa_Ruc;
    }

    public String getCliente_Tipo_Doc() {
        return cliente_Tipo_Doc;
    }

    public void setCliente_Tipo_Doc(String cliente_Tipo_Doc) {
        this.cliente_Tipo_Doc = cliente_Tipo_Doc;
    }

    public String getCliente_Num_Doc() {
        return cliente_Num_Doc;
    }

    public void setCliente_Num_Doc(String cliente_Num_Doc) {
        this.cliente_Num_Doc = cliente_Num_Doc;
    }

    public String getCliente_Razon_Social() {
        return cliente_Razon_Social;
    }

    public void setCliente_Razon_Social(String cliente_Razon_Social) {
        this.cliente_Razon_Social = cliente_Razon_Social;
    }

    public String getCliente_Direccion() {
        return cliente_Direccion;
    }

    public void setCliente_Direccion(String cliente_Direccion) {
        this.cliente_Direccion = cliente_Direccion;
    }

    public double getMonto_Oper_Gravadas() {
        return monto_Oper_Gravadas;
    }

    public void setMonto_Oper_Gravadas(double monto_Oper_Gravadas) {
        this.monto_Oper_Gravadas = monto_Oper_Gravadas;
    }

    public double getMonto_Igv() {
        return monto_Igv;
    }

    public void setMonto_Igv(double monto_Igv) {
        this.monto_Igv = monto_Igv;
    }

    public double getTotal_Impuestos() {
        return total_Impuestos;
    }

    public void setTotal_Impuestos(double total_Impuestos) {
        this.total_Impuestos = total_Impuestos;
    }

    public double getValor_Venta() {
        return valor_Venta;
    }

    public void setValor_Venta(double valor_Venta) {
        this.valor_Venta = valor_Venta;
    }

    public double getSub_Total() {
        return sub_Total;
    }

    public void setSub_Total(double sub_Total) {
        this.sub_Total = sub_Total;
    }

    public double getMonto_Imp_Venta() {
        return monto_Imp_Venta;
    }

    public void setMonto_Imp_Venta(double monto_Imp_Venta) {
        this.monto_Imp_Venta = monto_Imp_Venta;
    }

    public double getMonto_Oper_Exoneradas() {
        return monto_Oper_Exoneradas;
    }

    public void setMonto_Oper_Exoneradas(double monto_Oper_Exoneradas) {
        this.monto_Oper_Exoneradas = monto_Oper_Exoneradas;
    }

    public String getEstado_Documento() {
        return estado_Documento;
    }

    public void setEstado_Documento(String estado_Documento) {
        this.estado_Documento = estado_Documento;
    }

    public boolean isManual() {
        return manual;
    }

    public void setManual(boolean manual) {
        this.manual = manual;
    }

    public String getId_Base_Dato() {
        return id_Base_Dato;
    }

    public void setId_Base_Dato(String id_Base_Dato) {
        this.id_Base_Dato = id_Base_Dato;
    }

    public List<Detalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<Detalle> detalle) {
        this.detalle = detalle;
    }

    public List<FormaPago> getForma_pago() {
        return forma_pago;
    }

    public void setForma_pago(List<FormaPago> forma_pago) {
        this.forma_pago = forma_pago;
    }

    public List<Legend> getLegend() {
        return legend;
    }

    public void setLegend(List<Legend> legend) {
        this.legend = legend;
    }

    public static class Detalle {
        public String unidad;
        public int cantidad;
        public String cod_Producto;
        public String descripcion;
        public double monto_Valor_Unitario;
        public double monto_Base_Igv;
        public double porcentaje_Igv;
        public double igv;
        public String tip_Afe_Igv;
        public double total_Impuestos;
        public double monto_Precio_Unitario;
        public double monto_Valor_Venta;
        public double factor_Icbper;

        public Detalle(){}

        public String getUnidad() {
            return unidad;
        }

        public void setUnidad(String unidad) {
            this.unidad = unidad;
        }

        public int getCantidad() {
            return cantidad;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public String getCod_Producto() {
            return cod_Producto;
        }

        public void setCod_Producto(String cod_Producto) {
            this.cod_Producto = cod_Producto;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public double getMonto_Valor_Unitario() {
            return monto_Valor_Unitario;
        }

        public void setMonto_Valor_Unitario(double monto_Valor_Unitario) {
            this.monto_Valor_Unitario = monto_Valor_Unitario;
        }

        public double getMonto_Base_Igv() {
            return monto_Base_Igv;
        }

        public void setMonto_Base_Igv(double monto_Base_Igv) {
            this.monto_Base_Igv = monto_Base_Igv;
        }

        public double getPorcentaje_Igv() {
            return porcentaje_Igv;
        }

        public void setPorcentaje_Igv(double porcentaje_Igv) {
            this.porcentaje_Igv = porcentaje_Igv;
        }

        public double getIgv() {
            return igv;
        }

        public void setIgv(double igv) {
            this.igv = igv;
        }

        public String getTip_Afe_Igv() {
            return tip_Afe_Igv;
        }

        public void setTip_Afe_Igv(String tip_Afe_Igv) {
            this.tip_Afe_Igv = tip_Afe_Igv;
        }

        public double getTotal_Impuestos() {
            return total_Impuestos;
        }

        public void setTotal_Impuestos(double total_Impuestos) {
            this.total_Impuestos = total_Impuestos;
        }

        public double getMonto_Precio_Unitario() {
            return monto_Precio_Unitario;
        }

        public void setMonto_Precio_Unitario(double monto_Precio_Unitario) {
            this.monto_Precio_Unitario = monto_Precio_Unitario;
        }

        public double getMonto_Valor_Venta() {
            return monto_Valor_Venta;
        }

        public void setMonto_Valor_Venta(double monto_Valor_Venta) {
            this.monto_Valor_Venta = monto_Valor_Venta;
        }

        public double getFactor_Icbper() {
            return factor_Icbper;
        }

        public void setFactor_Icbper(double factor_Icbper) {
            this.factor_Icbper = factor_Icbper;
        }
    }

    public static class FormaPago {
        public String tipo;
        public double monto;
        public int cuota;
        public String fecha_Pago;

        public FormaPago(){}
        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public double getMonto() {
            return monto;
        }

        public void setMonto(double monto) {
            this.monto = monto;
        }

        public int getCuota() {
            return cuota;
        }

        public void setCuota(int cuota) {
            this.cuota = cuota;
        }

        public String getFecha_Pago() {
            return fecha_Pago;
        }

        public void setFecha_Pago(String fecha_Pago) {
            this.fecha_Pago = fecha_Pago;
        }
    }

    public static class Legend {
        public String legend_Code;
        public String legend_Value;

        public Legend(){}

        public String getLegend_Code() {
            return legend_Code;
        }

        public void setLegend_Code(String legend_Code) {
            this.legend_Code = legend_Code;
        }

        public String getLegend_Value() {
            return legend_Value;
        }

        public void setLegend_Value(String legend_Value) {
            this.legend_Value = legend_Value;
        }
    }
}