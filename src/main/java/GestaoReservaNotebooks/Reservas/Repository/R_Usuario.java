package GestaoReservaNotebooks.Reservas.Repository;

import GestaoReservaNotebooks.Reservas.Model.M_Notebook;
import GestaoReservaNotebooks.Reservas.Model.M_Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface R_Usuario extends JpaRepository<M_Usuario, Long>{


    @Query(value = "SELECT * FROM pessoa WHERE id = :id", nativeQuery = true)
    M_Usuario findById(@Param("id")String id);

    @Query(value = "SELECT * FROM pessoa WHERE matricula = :matricula and senha = :senha", nativeQuery = true)
    M_Usuario findByMatriculaESenha(@Param("matricula")Long matricula, @Param("senha")String senha);


}
