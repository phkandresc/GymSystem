package service;

import DAO.MembresiaDAO;

import model.Membresia;
import model.Socio;
import model.TipoMembresia;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MembresiaService {
    private MembresiaDAO membresiaDAO;

    public MembresiaService() {
        this.membresiaDAO = new MembresiaDAO();
    }

    public void registrarMembresia(Socio socio, TipoMembresia tipoMembresia) throws Exception {
        Calendar calendar = Calendar.getInstance();
        Date fechaInicio = new Date(calendar.getTimeInMillis());
        calendar.add(Calendar.DAY_OF_MONTH, tipoMembresia.getDuracion());
        Date fechaFin = new Date(calendar.getTimeInMillis());

        Membresia membresia = new Membresia(
                socio.getId(),
                tipoMembresia.getId(),
                1,
                fechaInicio,
                fechaFin
        );
        membresiaDAO.registrarMembresia(membresia);
    }

}
