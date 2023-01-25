package fenoreste.inspei.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="spei_entrada_temporal_cola_guardado")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpeiTemporal implements Serializable{
	 
	   @Id
	   @GeneratedValue(generator="sec_spei_temporal") 
	   @SequenceGenerator(name="sec_spei_temporal",sequenceName="sec_spei_temporal", allocationSize=1)
	   private Integer id;	    
       private boolean aplicado;    
	   private Integer idusuario;   
	   private String sesion;       
	   private Integer idorigen;    
	   private Integer idgrupo;     
	   private Integer idsocio;     
	   private Integer idorigenp;
	   private Integer idproducto;
	   private Integer idauxiliar;  
	   private boolean esentrada;   
	   private Double acapital;     
	   private Double io_pag;       
	   private Double io_cal;   
	   @Temporal(TemporalType.DATE)
       private Date fecha_inserta;  
	   private Double im_pag;       
	   private Double im_cal;           
	   private Double aiva;             
	   private Double saldodiacum;      
	   private Double abonifio;         
	   private String idcuenta;         
	   private Double ivaio_pag;        
	   private Double ivaio_cal;        
	   private Double ivaim_pag;        
	   private Double ivaim_cal;        
	   private Integer mov;             
	   private Integer tipomov;
	   private String referencia;
	   private Integer diasvencidos;
	   private Double montovencido;
	   private Integer idorigena;
	   private boolean huella_valida;
	   private String concepto_mov;
	   private String fe_nom_archivo;
	   private String fe_xml;
	   private String sai_aux;
	   @Temporal(TemporalType.DATE)
	   private Date fecha_hora_system;
	   private String poliza_generada;
	   private Date fecha_aplicado;
	   
	private static final long serialVersionUID = 1L;

}
