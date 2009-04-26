/**
 * 
 */
package ar.com.datos.grupo5.btree;

import ar.com.datos.grupo5.registros.RegistroNodo;

/**
 * Clase que implementa el nodo de los �rboles B*.
 * @author Led Zeppelin
 */
public final class BAsterisk implements BTree {
	
	/**
	 * Nodo Actual.
	 */
	private Nodo nodoActual;
	
	/**
	 * Nodo Raiz.
	 */
	private Nodo nodoRaiz;

	/**
	 * Buscar recursivo.
	 * @param clave
	 * @param nodo
	 * @return
	 */
	public RegistroNodo buscarRec(final Clave clave, Nodo nodo) {
		
		Nodo nodoAux = nodo;
		RegistroNodo registro = null;
		
		if (nodo == null) {
			nodoAux = nodoRaiz;
		}
		
		int posReg = nodoAux.buscarRegistro(clave);
		switch (posReg) {
		case -1: //La clave es menor al primero, voy por la izquierda.
			
			if (!nodoAux.isEsHoja()) {
				buscarRec(clave, nodoAux.getNodos().get(0));
			} else {
				registro = nodoAux.getPrimerRegistro();
			}
			break;
			
		case -2:
			if (!nodoAux.isEsHoja()) {
				buscarRec(clave, nodoAux.getNodos().get(
						nodoAux.getNodos().size()));
			} else {
				registro = nodoAux.getUltimoRegistro();
			}
			break;
			
		default:
			//Lo que encontre el igual.
			if (nodoAux.getRegistros().get(posReg).getClaveNodo()
					.equals(clave)) {
				
				registro = nodoAux.getRegistros().get(posReg);
			} else { // Es mayor
				if (!nodoAux.isEsHoja()) {
					buscarRec(clave, nodoAux);
				} else {
					registro = nodoAux.getRegistros().get(posReg);
				}
			}
			break;
		}
		
		return registro;
	}
	
	/**
	 * Busca un registro.
	 * 
	 * @param clave
	 *            la clave del registro.
	 * @return El registro buscado o el siguiente inmediatamente mayor.
	 */
	public RegistroNodo buscar(final Clave clave) {
		
		//FIXME: Testear!!!!!!!!!
		Nodo nodo = buscarNodo(clave);
		
		int posReg = nodo.buscarRegistro(clave); 
		switch (posReg) {
		case -1:
			return nodo.getPrimerRegistro();

		case -2:
			return nodo.getUltimoRegistro();
			
		default:
			
			return nodo.getRegistros().get(posReg);
		} 
	}

	/**
	 * Devuelve el nodo en el cual podria insertarse o encontrarse un registro.
	 * 
	 * @param clave .
	 * @return El nodo buscado.
	 */
	public final Nodo buscarNodo(final Clave clave) {
		
		if (nodoRaiz == null) {
			return null;
		}
		
		Nodo nodoAux = nodoRaiz;
		int posReg = 0;
		
		while (nodoAux != null) {
			
			//Busco la clave en el nodo.
			posReg = nodoAux.buscarRegistro(clave);
			
			switch (posReg) {
			case -1: //La clave es menor al primero, voy por la izquierda.
				if (!nodoAux.isEsHoja()) {
					nodoAux = nodoAux.getNodos().get(0);
				} else {
					return nodoAux;
				}
				break;
				
			case -2: //La clave es mayor al ultimo, voy por la derecha.
				if (!nodoAux.isEsHoja()) {
					nodoAux = nodoAux.getNodos().get(nodoAux.getNodos().size());
				} else {
					return nodoAux;
				}
				break;
				
			default: //Encontr� la clave que buscaba o una mayor.
				//Veo si lo que recupere el igual o mayor.
				if (nodoAux.getRegistros().get(posReg).getClaveNodo().equals(
						clave)) {
					
					return nodoAux;
				} else { // Es mayor.
					if (!nodoAux.isEsHoja()) {
						nodoAux = nodoAux.getNodos().get(posReg);
					} else {
						return nodoAux;
					}
				}
			}
		}
		return nodoAux;
	}
	
	/**
	 * @see ar.com.datos.grupo5.btree.BTree#insertar(ar.com.datos.grupo5.interfaces.Registro)
	 */
	public boolean insertar(final RegistroNodo registro) {
		// TODO Auto-generated method stub
		if(this.nodoRaiz == null) {
			this.nodoRaiz = new Nodo();
			this.nodoRaiz.insertarRegistro(registro);
		}
		
		return false;
	}

	/**
	 * @see ar.com.datos.grupo5.btree.BTree#modificar(ar.com.datos.grupo5.interfaces.Registro)
	 */
	public boolean modificar(final RegistroNodo registro) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see ar.com.datos.grupo5.btree.BTree#siguiente()
	 */
	public RegistroNodo siguiente() {
		// TODO Auto-generated method stub
		return null;
	}

}