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
				nodoAux.getUltimoRegistro();
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
		
		if (nodoRaiz == null) {
			return null;
		}
		
		Nodo nodoAux = nodoRaiz;
		int posReg = 0;
		RegistroNodo registro = null;
		
		while (nodoAux != null) {
			
			//Busco la clave en el nodo.
			posReg = nodoAux.buscarRegistro(clave);
			
			switch (posReg) {
			case -1: //La clave es menor al primero, voy por la izquierda.
				if (!nodoAux.isEsHoja()) {
					nodoAux = nodoAux.getNodos().get(0);
				} else {
					registro = nodoAux.getRegistros().get(0);
					nodoAux = null;
				}
				break;
				
			case -2: //La clave es mayor al ultimo, voy por la derecha.
				if (!nodoAux.isEsHoja()) {
					nodoAux = nodoAux.getNodos().get(nodoAux.getNodos().size());
				} else {
					registro = nodoAux.getRegistros().get(
							nodoAux.getNodos().size());
					nodoAux = null;
				}
				break;
				
			default: //Encontr� la clave que buscaba o una mayor.
				//Veo si lo que recupere el igual o mayor.
				if (nodoAux.getRegistros().get(posReg).getClaveNodo().equals(
						clave)) {
					
					return nodoAux.getRegistros().get(posReg);
				} else { // Es mayor.
					if (!nodoAux.isEsHoja()) {
						nodoAux = nodoAux.getNodos().get(posReg);
					} else {
						registro = nodoAux.getRegistros().get(posReg);
						nodoAux = null;
					}
				}
			}
		}
		
		return registro;
	}

	/**
	 * @see ar.com.datos.grupo5.btree.BTree#insertar(ar.com.datos.grupo5.interfaces.Registro)
	 */
	public boolean insertar(final RegistroNodo registro) {
		// TODO Auto-generated method stub
		if(this.nodoRaiz == null) {
			this.nodoRaiz = new Nodo();
			this.nodoRaiz.insertarRegistro(registro);
			this.nodoRaiz.setEsHoja(true);
			
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
