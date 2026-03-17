package es.unican.is2.business;

import es.unican.is2.common.Cliente;
import es.unican.is2.common.Seguro; 
import es.unican.is2.common.DataAccessException;
import es.unican.is2.common.OperacionNoValida;
import es.unican.is2.common.IClientesDAO;
import es.unican.is2.common.ISegurosDAO;
import es.unican.is2.common.IGestionClientes;
import es.unican.is2.common.IGestionSeguros;
import es.unican.is2.common.IInfoSeguros;

public class GestionSeguros implements IGestionClientes, IGestionSeguros, IInfoSeguros {

    private IClientesDAO clientesDAO;
    private ISegurosDAO segurosDAO;

    public GestionSeguros(IClientesDAO clientesDAO, ISegurosDAO segurosDAO) {
        this.clientesDAO = clientesDAO;
        this.segurosDAO = segurosDAO;
    }

    @Override
    public Cliente nuevoCliente(Cliente c) throws DataAccessException {
        Cliente existente = clientesDAO.cliente(c.getDni());
        if (existente != null) {
            return null; // ya existe
        }
        return clientesDAO.creaCliente(c);
    }

    @Override
    public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            return null; // no existe
        }

        if (!c.getSeguros().isEmpty()) {
            throw new OperacionNoValida("El cliente tiene seguros activos");
        }

        return clientesDAO.eliminaCliente(dni);
    }

    @Override
    public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            return null; 
        }

        if (segurosDAO.seguroPorMatricula(s.getMatricula()) != null) {
            throw new OperacionNoValida("El seguro ya existe");
        }

        c.getSeguros().add(s);

        segurosDAO.creaSeguro(s);
        clientesDAO.actualizaCliente(c);

        return s;
    }

    @Override
    public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
        Cliente c = clientesDAO.cliente(dni);
        if (c == null) {
            return null; 
        }

        Seguro s = segurosDAO.seguroPorMatricula(matricula);
        if (s == null) {
            return null;
        }

        if (!c.getSeguros().contains(s)) {
            throw new OperacionNoValida("El seguro no pertenece al cliente");
        }

        c.getSeguros().remove(s);

        segurosDAO.eliminaSeguro(s.getId());

        clientesDAO.actualizaCliente(c);

        return s;
    }

    @Override
    public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
        Seguro s = segurosDAO.seguroPorMatricula(matricula);
        if (s == null) {
            return null;
        }

        s.setConductorAdicional(conductor);
        return segurosDAO.actualizaSeguro(s);
    }

    @Override
    public Cliente cliente(String dni) throws DataAccessException {
        return clientesDAO.cliente(dni);
    }

    @Override
    public Seguro seguro(String matricula) throws DataAccessException {
        return segurosDAO.seguroPorMatricula(matricula);
    }
}
