package es.unican.is2.main;

import es.unican.is2.gui.VistaAgente;
import es.unican.is2.business.GestionSeguros;
import es.unican.is2.daoh2.IClientesDAO;
import es.unican.is2.daoh2.ISegurosDAO;
import es.unican.is2.daoh2.ClientesDAO;
import es.unican.is2.daoh2.SegurosDAO;

public class Runner {

	public static void main(String[] args) {
		IClientesDAO daoClientes = new ClientesDAO();
		ISegurosDAO daoSeguros = new SegurosDAO();
		GestionSeguros negocio = new GestionSeguros(daoClientes, daoSeguros);
		VistaAgente vista = new VistaAgente(negocio, negocio, negocio);
		vista.setVisible(true);
	}

}
