
DROP TABLE IF EXISTS spei_entrada_temporal_cola_guardado;
CREATE TABLE spei_entrada_temporal_cola_guardado (
    fecha_inserta       timestamp without time zone DEFAULT clock_timestamp(),
    aplicado            boolean DEFAULT false,
    idusuario           integer,
    sesion              character varying(20),
    idorigen            integer,
    idgrupo             integer,
    idsocio             integer,
    idorigenp           integer,
    idproducto          integer,
    idauxiliar          integer,
    esentrada           boolean,
    acapital            numeric(12,2),
    io_pag              numeric(10,2),
    io_cal              numeric(10,2),
    im_pag              numeric(10,2),
    im_cal              numeric(10,2),
    aiva                numeric(10,2),
    saldodiacum         numeric(12,2),
    abonifio            numeric(10,2),
    idcuenta            character varying(20),
    ivaio_pag           numeric(10,2),
    ivaio_cal           numeric(10,2),
    ivaim_pag           numeric(10,2),
    ivaim_cal           numeric(10,2),
    mov                 integer,
    tipomov             integer DEFAULT 0 NOT NULL,
    referencia          text,
    diasvencidos        integer DEFAULT 0 NOT NULL,
    montovencido        numeric(12,2) DEFAULT 0 NOT NULL,
    idorigena           integer,
    huella_valida       boolean DEFAULT false,
    concepto_mov        text,
    fe_nom_archivo      text,
    fe_xml              text,
    sai_aux             text,
    fecha_hora_system   timestamp without time zone DEFAULT now(),
    poliza_generada     text,
    fecha_aplicado      timestamp without time zone    
);

create index spei_entrada_temporal_cola_guardado_idx on spei_entrada_temporal_cola_guardado (fecha_inserta);


DROP TABLE IF EXISTS speirecibido;
CREATE TABLE speirecibido(
 clave                    text,	 
 fechaOperacion           Integer,
 institucionOrdenante     text,
 institucionBeneficiaria  text,
 claveRastreo             text,
 monto                    numeric,
 nombreOrdenante          text,
 tipocuentaOrdenante      text,
 cuentaOrdenante          text,
 rfccurpOrdenante         text,
 nombreBeneficiario       text,
 tipocuentaBeneficiario   text,
 cuentaBeneficiario       text,
 rfcCurpBeneficiario      text,
 conceptoPago             text,
 referenciaNumerica       text,
 empresa                  text,
 fechaProcesada              timestamp without time zone);

