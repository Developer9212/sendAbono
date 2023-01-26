package fenoreste.inspei.service;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.consumo.ConsumoCsnTDD;
import fenoreste.inspei.entity.AbonoSpei;
import fenoreste.inspei.entity.Auxiliar;
import fenoreste.inspei.entity.AuxiliarPK;
import fenoreste.inspei.entity.ClabeInterbancaria;
import fenoreste.inspei.entity.FolioTarjeta;
import fenoreste.inspei.entity.Origen;
import fenoreste.inspei.entity.SpeiTemporal;
import fenoreste.inspei.entity.Tabla;
import fenoreste.inspei.entity.TablaPK;
import fenoreste.inspei.entity.Tarjeta;
import fenoreste.inspei.entity.Usuario;
import fenoreste.inspei.modelos.request;
import fenoreste.inspei.modelos.response;

@Service
public class InServiceGeneral {
    
	@Autowired
	private IFuncionesSaiService funcionesSaiService;
	
	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private ITablaService tablasService;
	
	@Autowired
	private IOrigenService origenesService;
	
	@Autowired
	private IClabeInterbancariaService clabeInterbancariaService;
	
	@Autowired 
	private IAbonoSpeiService abonoSpeiService;
	
	@Autowired
	private IFolioTarjetaService folioTarjetaService;
	
	@Autowired
	private ITarjetaService tarjetaService;
	
	@Autowired
	private IAuxiliarService auxiliarService;
	
	@Autowired
	private ISpeiTemporalService speiTemporalService;
	
	@Autowired
	private ConsumoCsnTDD consumoCsnTDD;
	
	String idtabla="spei_entrada";
	
	public response response(request in) {
	    response resp = new response();
	    
	    //validamos el horario de actividad
	    if(funcionesSaiService.horario_actividad()) {
	    	//Obtenemos origen Matriz
	    	Origen matriz = origenesService.buscarMatriz();
	    	//Buscamos el usuario para operar abonos
	    	TablaPK tb_pk= new TablaPK(idtabla,"usuario");
	    	Tabla tb_usuario = tablasService.buscarPorId(tb_pk);
	    	Usuario user_in = usuarioService.buscar(new Integer(tb_usuario.getDato1()));
	    	//Obtenemos el estatus de origen al que pertenece el usuario
	    	Origen origen_usuario = origenesService.buscarPorId(new Integer(user_in.getIdorigen()));
	    	if(origen_usuario.isEstatus()) {
	    		//Buscamos minimo y maximo a operar
	    		tb_pk.setIdElemento("monto_minimo");
	    		Tabla tb_minimo = tablasService.buscarPorId(tb_pk);
	    		tb_pk.setIdElemento("monto_maximo");
	    		Tabla tb_maximo = tablasService.buscarPorId(tb_pk);
	    		Double monto_minimo = new Double(tb_minimo.getDato1());
	    		Double monto_maximo = new Double(tb_maximo.getDato1());
	    		if(in.getMonto() >= monto_minimo) {
	    			if(in.getMonto() <= monto_maximo) {
	    				//Buscamos registro para la clabe
	    				switch (in.getTipoCuentaBeneficiario()) {
						case 40:
							ClabeInterbancaria clabe_registro = clabeInterbancariaService.buscarPorClabe(in.getCuentaBeneficiario());
							if(clabe_registro != null) {
								      	//obtenemos el opa que pertenece a la clabe
										AuxiliarPK pk_clabe = clabe_registro.getAuxPk();
								        FolioTarjeta folioTarjeta = folioTarjetaService.buscarPorId(pk_clabe);
								        if(folioTarjeta != null) {
								        	  //Buscamos registro para tarjeta
								        	  Tarjeta tarjeta = tarjetaService.buscarPorId(folioTarjeta.getIdtarjeta());
								        	 if(tarjeta != null) {
								        		    //Validamos fecha de vencimiendo
								        		    if(tarjeta.getFecha_vencimiento().after(matriz.getFechatrabajo())) {
								        		    	//Validamos monto maximo diario
														tb_pk.setIdElemento("monto_maximo_diario");
														Tabla tb_monto_maximo_diario = tablasService.buscarPorId(tb_pk);
							                            List<AbonoSpei>abonos = abonoSpeiService.todasPorFecha(in.getFechaOperacion());
							                            Double acumulado = 0.0;
							                            for(int i=0;i<abonos.size();i++) {
							                            	acumulado = acumulado + abonos.get(i).getMonto();
							                            }
							                            if((acumulado + in.getMonto()) < new Double(tb_monto_maximo_diario.getDato1())) {
							                            	//registramos los movimientos a temporal
							                            	SpeiTemporal temporal = new SpeiTemporal();
							                            	//Obtengo datos de Auxiliar TDD
							                            	Auxiliar folio_tdd_auxiliar = auxiliarService.buscarPorId(folioTarjeta.getPk());
							                            	//Buscamos tabla para comision
							                            	TablaPK tb_pk_comision = new TablaPK(idtabla,"monto_comision");
							                            	Tabla tb_comision = tablasService.buscarPorId(tb_pk_comision);
							                            	//Buscamos tabla para producto comision
							                                tb_pk_comision = new TablaPK(idtabla,"producto_comision");
							                                Tabla tb_producto_comision = tablasService.buscarPorId(tb_pk_comision);
							                                
							                                //Buscamos tabla para producto comision
							                                tb_pk_comision = new TablaPK(idtabla,"producto_iva_comision");
							                                Tabla tb_producto_iva_comision = tablasService.buscarPorId(tb_pk_comision);
							                                
							                                //Buscamos tabla para idcuenta
							                                TablaPK tb_pk_cuenta = new TablaPK(idtabla,"cuenta_contable");
							                                Tabla tb_cuenta_contable = tablasService.buscarPorId(tb_pk_cuenta);
							                            	
							                            	//Vamos a registrar movimiento a tdd(Abono)
							                            	temporal.setIdorigen(folio_tdd_auxiliar.getIdorigen());
							                            	temporal.setIdgrupo(folio_tdd_auxiliar.getIdgrupo());
							                            	temporal.setIdsocio(folio_tdd_auxiliar.getIdsocio());
							                            	temporal.setIdorigenp(folio_tdd_auxiliar.getAuxiliarPK().getIdorigenp());
							                            	temporal.setIdproducto(folio_tdd_auxiliar.getAuxiliarPK().getIdproducto());
							                            	temporal.setIdauxiliar(folio_tdd_auxiliar.getAuxiliarPK().getIdauxiliar());
							                            	temporal.setEsentrada(true);
							                            	temporal.setAcapital(in.getMonto());
							                                temporal.setReferencia(String.valueOf(in.getReferenciaNumerica()));
							                                temporal.setIdusuario(Integer.parseInt(tb_usuario.getDato1()));
							                                temporal.setSesion(funcionesSaiService.session());
							                                String sai = funcionesSaiService.sai_auxiliar(new AuxiliarPK(temporal.getIdorigenp(), temporal.getIdproducto(), temporal.getIdauxiliar()));
							                                temporal.setSai_aux(sai);							                                
							                                speiTemporalService.guardar(temporal);
							                                
							                              
							                                
							                                //Vamos a registrar el movimiento a cuentaContable(Cargo)
							                                temporal = new SpeiTemporal();
							                                temporal.setIdcuenta(tb_cuenta_contable.getDato1());
							                                temporal.setIdorigen(folio_tdd_auxiliar.getIdorigen());
							                            	temporal.setIdgrupo(folio_tdd_auxiliar.getIdgrupo());
							                            	temporal.setIdsocio(folio_tdd_auxiliar.getIdsocio());
							                            	temporal.setEsentrada(false);
							                            	temporal.setAcapital(in.getMonto());
							                                temporal.setReferencia(String.valueOf(in.getReferenciaNumerica()));
							                                temporal.setIdusuario(Integer.parseInt(tb_usuario.getDato1()));
							                                temporal.setSesion(funcionesSaiService.session());							                                
							                                speiTemporalService.guardar(temporal);
							                                
							                                //Se han preparado los movimientos es hora de enviar a la tdd
							                                //Buscamos la tabla donde esta la url
							                                TablaPK pk_url_tdd = new TablaPK(idtabla,"url_tdd");
							                                Tabla tb_url_tdd = tablasService.buscarPorId(pk_url_tdd);
							                                boolean bandera =  true;//consumoCsnTDD.depositarSaldo(tb_url_tdd.getDato2(),tarjeta.getIdtarjeta(),in.getMonto());
							                                if(bandera) {
							                                	//vamos a general poliza(cargo cuenta spei y abono tdd)
							                                	Integer movs_aplicados = 0;//funcionesSaiService.aplica_movs(Integer.parseInt(tb_usuario.getDato1()), temporal.getSesion());
							                                	if(movs_aplicados > 10) {
							                                		//Eliminamos temporales para no tener problemas
							                                		//speiTemporalService.eliminar(temporal.getSesion());
							                                		
							                                		//vamos a descontar la comision
								                                	//Vamos a registrar movimiento a tdd(Cargo)
							                                		temporal = new SpeiTemporal();
									                            	temporal.setIdorigen(folio_tdd_auxiliar.getIdorigen());
									                            	temporal.setIdgrupo(folio_tdd_auxiliar.getIdgrupo());
									                            	temporal.setIdsocio(folio_tdd_auxiliar.getIdsocio());
									                            	temporal.setIdorigenp(folio_tdd_auxiliar.getAuxiliarPK().getIdorigenp());
									                            	temporal.setIdproducto(folio_tdd_auxiliar.getAuxiliarPK().getIdproducto());
									                            	temporal.setIdauxiliar(folio_tdd_auxiliar.getAuxiliarPK().getIdauxiliar());
									                            	temporal.setEsentrada(false);
									                            	temporal.setAcapital(Double.parseDouble(tb_comision.getDato1()) + (Double.parseDouble(tb_comision.getDato1()) * 0.16));
									                            	temporal.setReferencia(String.valueOf(in.getReferenciaNumerica()));
									                                temporal.setIdusuario(Integer.parseInt(tb_usuario.getDato1()));
									                                temporal.setSesion(funcionesSaiService.session()+1);
									                                String sai_c = funcionesSaiService.sai_auxiliar(new AuxiliarPK(temporal.getIdorigenp(), temporal.getIdproducto(), temporal.getIdauxiliar()));
									                                System.out.println(sai_c);
									                                temporal.setSai_aux(sai_c);
									                                speiTemporalService.guardar(temporal);
									                                
								                                	//Vamos a registrar movimiento al producto comision(Abono)
									                                temporal = new SpeiTemporal();
									                                temporal.setIdorigen(folio_tdd_auxiliar.getIdorigen());
									                            	temporal.setIdgrupo(folio_tdd_auxiliar.getIdgrupo());
									                            	temporal.setIdsocio(folio_tdd_auxiliar.getIdsocio());
									                            	temporal.setIdproducto(Integer.parseInt(tb_producto_comision.getDato1()));							                            	
									                            	temporal.setEsentrada(true);
									                            	temporal.setAcapital(Double.parseDouble(tb_comision.getDato1()));									                            	
									                                temporal.setReferencia(String.valueOf(in.getReferenciaNumerica()));
									                                temporal.setIdusuario(Integer.parseInt(tb_usuario.getDato1()));
									                                temporal.setSesion(funcionesSaiService.session() + 1);								                                
									                                speiTemporalService.guardar(temporal);
									                                
									                               //Vamos a registrar movimiento al producto iva comision(Abono)
									                                temporal = new SpeiTemporal();
									                                temporal.setIdorigen(folio_tdd_auxiliar.getIdorigen());
									                            	temporal.setIdgrupo(folio_tdd_auxiliar.getIdgrupo());
									                            	temporal.setIdsocio(folio_tdd_auxiliar.getIdsocio());
									                            	temporal.setIdproducto(Integer.parseInt(tb_producto_iva_comision.getDato1()));							                            	
									                            	temporal.setEsentrada(true);
									                            	temporal.setAcapital(Double.parseDouble(tb_comision.getDato1()) * 0.16);
									                                temporal.setReferencia(String.valueOf(in.getReferenciaNumerica()));
									                                temporal.setIdusuario(Integer.parseInt(tb_usuario.getDato1()));
									                                temporal.setSesion(funcionesSaiService.session()+1);	
									                                speiTemporalService.guardar(temporal);
									                                movs_aplicados = funcionesSaiService.aplica_movs(Integer.parseInt(tb_usuario.getDato1()), temporal.getSesion());
								                                	
									                                
									                               //speiTemporalService.eliminar(temporal.getSesion());
									                           
							                                	}else {
							                                	    bandera = consumoCsnTDD.retirarSaldo(tb_url_tdd.getDato2(),tarjeta.getIdtarjeta(),in.getMonto());	
							                                	}
							                                }
							                                
							                                
							                            }else {
							                            	resp.setMensaje("Monto traspasa el permitido diario");
							                            }	
								        		    }else {
								        		      resp.setMensaje("La tarjeta esta vencida");
								        		    }								        	    	
								        	  }else {
								        	     resp.setMensaje("No eixtsen resgistros en tarjeta para:"+folioTarjeta.getIdtarjeta());   	
								        	 }
								            	
								        }else {
								          resp.setMensaje("No existen registros folio para clabe:"+clabe_registro.getClabe());       	
								        }
							       }else {
								     resp.setMensaje("No existen registros para la cuenta:"+in.getCuentaBeneficiario());
							     }
							      break;
					        	default:
							      break;
						}//Fin de switch
	    			}else {
	    				resp.setMensaje("Monto mayor a lo permitido en el core");
	    			}
	    		}else {
	    		   resp.setMensaje("Monto menor a lo permitido en el core");	
	    		}	
	    	}else {
	    	  	resp.setMensaje("Estatus no valido para operar para origen:"+origen_usuario.getIdorigen());
	    	}
	    }else {
	    	resp.setMensaje("Operacion fuera de horario");
	    }
	    System.exit(0);
		return resp; 
		
	}
	
	
 
}
