package ar.com.datos.grupo5.archivos;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ar.com.datos.grupo5.CFFTRS;
import ar.com.datos.grupo5.registros.RegistroFTRS;
import ar.com.datos.grupo5.trie.persistence.Contenedor;

public class BloqueFTRS {

	private ArrayList<RegistroFTRS> listaTerminosFrontCodeados;
	
	private ArrayList<CFFTRS> listaTerminosSinFrontCodear;
	
	
	
	
	
	public BloqueFTRS(byte[] chocloBytes){
		this.setBytes(chocloBytes);
	}
	
	public BloqueFTRS (Collection<CFFTRS> lista){
		
	}
	
	
	public byte[] getBytes(){
		
		//genero los manipuladores de bytes
		ByteArrayOutputStream bos = new ByteArrayOutputStream();  
		DataOutputStream dos = new DataOutputStream(bos);
		
		//obtengo un iterador de la lista de registros
		Iterator<RegistroFTRS> it = listaTerminosFrontCodeados.iterator();
		
		//itero sobre la lista serializando los contenedores.
		while(it.hasNext()){
			try {
				
				dos.write(it.next().getContenidoEmpaquetado().serializar());
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		//genero un contenedor general que va a almacenar la coleccion de registros serializados
		//es un contenedor de contenedores
		Contenedor contGeneral = new Contenedor();
		
		//le seteo el dato
		contGeneral.setDato(bos.toByteArray());
		
		//devuelvo el contenedor serializado
		return contGeneral.serializar();
		
	}
	
	
	public void setBytes (byte[] tiraBytes){
		
		ArrayList<Contenedor> listaConts = Contenedor.rehidratarLista(tiraBytes);		
		
		Iterator<Contenedor> it = listaConts.iterator();
		
		while (it.hasNext()){
			
			RegistroFTRS rf = new RegistroFTRS(it.next());
			listaTerminosFrontCodeados.add(rf);
			
		}
	}
	
	
	
	
	
	
	
}
