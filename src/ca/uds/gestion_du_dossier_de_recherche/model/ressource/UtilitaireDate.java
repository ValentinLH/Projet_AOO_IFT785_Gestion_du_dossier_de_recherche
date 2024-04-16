package ca.uds.gestion_du_dossier_de_recherche.model.ressource;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.temporal.ChronoUnit;

public class UtilitaireDate {

    public static int calculerJoursOuvrables(LocalDate debut, LocalDate fin) {
        int joursOuvrables = 0;
        LocalDate dateCourante = debut;

        while (dateCourante.isBefore(fin) || dateCourante.equals(fin)) {
            if (!(dateCourante.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    dateCourante.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                joursOuvrables++;
            }
            dateCourante = dateCourante.plusDays(1);
        }

        return joursOuvrables;
    }
}
