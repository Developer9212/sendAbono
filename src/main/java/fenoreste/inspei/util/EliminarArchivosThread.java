package fenoreste.inspei.util;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class EliminarArchivosThread extends Thread {

	public EliminarArchivosThread () {
	}

	public void run() {
		boolean bandera = true;
		    while (bandera) {			
			eliminarArchivos();
			try {
				sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}
	
	public void eliminarArchivos() {
		  SimpleDateFormat dateFormatLocal = new SimpleDateFormat("HH:mm:ss");
	        String hora = dateFormatLocal.format(new Date());
	        //System.out.println("Hora:"+hora);
	        //eliminamos todos los PDF a las 1:00AM
	        if (hora.equals("23:59:59")) {
	            eliminarPorExtension(ruta(),"pdf");
	            eliminarPorExtension(ruta(),"html");
	            eliminarPorExtension(ruta(),"txt");
	            eliminarPorExtension(rutaVoucher(),"pdf");
	        } 
	}
	
	//Metodo para eliminar todos los pdf 
    public static void eliminarPorExtension(String path, final String extension) {
        File[] archivos = new File(path).listFiles(new FileFilter() {
            public boolean accept(File archivo) {
                if (archivo.isFile()) {
                    return archivo.getName().endsWith('.' + extension);
                }
                return false;
            }
        });
        for (File archivo : archivos) {
            archivo.delete();
        }
    }
	
	//Parao obtener la ruta del servidor
    public static String ruta() {
        String home = System.getProperty("user.home");
        String separador = System.getProperty("file.separator");
        String actualRuta = home + separador + "Banca" + separador;
        return actualRuta;
    }
    
    public static String rutaVoucher() {
        String home = System.getProperty("user.home");
        String separador = System.getProperty("file.separator");
        String actualRuta = home + separador + "Banca" + separador+"voucher";
        return actualRuta;
    }
}
