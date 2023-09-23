package GestaoReservaNotebooks.Reservas.Repository;

import GestaoReservaNotebooks.Reservas.Model.M_Notebook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface R_Notebook extends JpaRepository<M_Notebook, Long> {

}
