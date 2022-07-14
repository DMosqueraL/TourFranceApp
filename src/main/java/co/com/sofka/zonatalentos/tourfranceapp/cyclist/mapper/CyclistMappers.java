package co.com.sofka.zonatalentos.tourfranceapp.cyclist.mapper;

import co.com.sofka.zonatalentos.tourfranceapp.cyclist.collection.Cyclist;
import co.com.sofka.zonatalentos.tourfranceapp.cyclist.dto.CyclistDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class CyclistMappers {
    public Function<CyclistDTO, Cyclist> mapCyclistDTOToCyclist(){
        return updateCyclist -> {
            var cyclist = new Cyclist();
            cyclist.setIdCyclist(updateCyclist.getIdCyclist());
            cyclist.setName(updateCyclist.getName());
            cyclist.setNumCompetitor(updateCyclist.getNumCompetitor());
            cyclist.setNameTeam(updateCyclist.getNameTeam());
            cyclist.setNationality(updateCyclist.getNationality());

            return cyclist;
        };
    }

    public Function<Cyclist, CyclistDTO> mapCyclistToCyclistDTO(){
        return entity -> new CyclistDTO(
                entity.getIdCyclist(),
                entity.getName(),
                entity.getNumCompetitor(),
                entity.getNameTeam(),
                entity.getNationality()
        );
    }
}
