package nl.topicus.onderwijs.uwlr.v2.client;

import java.util.ArrayList;
import java.util.List;
import nl.topicus.onderwijs.generated.uwlr.v2_2.GroepType;
import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerkrachtType;
import nl.topicus.onderwijs.generated.uwlr.v2_2.LeerlingType;
import nl.topicus.onderwijs.generated.uwlr.v2_2.SamengesteldeGroepType;

public class UwlrConverter<LL, LK, G, SG> {

  private final UwlrEntiteitMapper<LL, LK, G, SG> mapper;

  private final UwlrMappingContext mappingContext;

  private final List<LL> leerlingen = new ArrayList<>();

  private final List<LK> leerkrachten = new ArrayList<>();

  private final List<G> groepen = new ArrayList<>();

  private final List<SG> samengesteldeGroepen = new ArrayList<>();

  public UwlrConverter(
      UwlrEntiteitMapper<LL, LK, G, SG> mapper, UwlrMappingContext mappingContext) {
    this.mapper = mapper;
    this.mappingContext = mappingContext;
  }

  public LL addLeerling(LeerlingType leerling) throws UwlrEntiteitMappingException {
    LL ll = mapper.mapLeerlingType(leerling, mappingContext);
    leerlingen.add(ll);
    return ll;
  }

  public LK addLeerkracht(LeerkrachtType leerkracht) throws UwlrEntiteitMappingException {
    LK lk = mapper.mapLeerkrachtType(leerkracht, mappingContext);
    leerkrachten.add(lk);
    return lk;
  }

  public G addGroep(GroepType groep) throws UwlrEntiteitMappingException {
    G g = mapper.mapGroepType(groep, mappingContext);
    groepen.add(g);
    return g;
  }

  public SG addSamengesteldeGroep(SamengesteldeGroepType groep)
      throws UwlrEntiteitMappingException {

    SG sg = mapper.mapSamengesteldeGroepType(groep, mappingContext);
    samengesteldeGroepen.add(sg);
    return sg;
  }

  public UwlrEntiteitMapper<LL, LK, G, SG> getMapper() {
    return mapper;
  }

  public UwlrMappingContext getMappingContext() {
    return mappingContext;
  }
}
