package fenoreste.inspei.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="speirecibido")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AbonoSpei implements Serializable {
	
	 @Id
	 private String  clave                    ;	 
	 private Integer fechaOperacion           ;
	 private String  institucionOrdenante     ;
	 private String  institucionBeneficiaria  ;
	 private String  claveRastreo             ;
	 private Double  monto                    ;
	 private String  nombreOrdenante          ;
	 private String  tipocuentaOrdenante      ;
	 private String  cuentaOrdenante          ;
	 private String  rfccurpOrdenante         ;
	 private String  nombreBeneficiario       ;
	 private String  tipocuentaBeneficiario   ;
	 private String  cuentaBeneficiario       ;
	 private String  rfcCurpBeneficiario      ;
	 private String  conceptoPago             ;
	 private String  referenciaNumerica       ;
	 private String  empresa                  ;
	 private Date fechaProcesada;
	 
	 private static final long serialVersionUID = 1L;
}
