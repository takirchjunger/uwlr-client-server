package nl.topicus.onderwijs.eck2.v2.client;

import java.util.ArrayList;
import java.util.List;

import nl.topicus.onderwijs.generated.uwlr.v2_0.GroepType;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerkrachtType;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlingType;
import nl.topicus.onderwijs.generated.uwlr.v2_0.SamengesteldeGroepType;

public class UwlrConverter<LL, LK, G, SG>
{

	private UwlrEntiteitMapper<LL, LK, G, SG> mapper;

	private UwlrMappingContext mappingContext;

	private List<LL> leerlingen = new ArrayList<>();

	private List<LK> leerkrachten = new ArrayList<>();

	private List<G> groepen = new ArrayList<>();

	private List<SG> samengesteldeGroepen = new ArrayList<>();

	public UwlrConverter(UwlrEntiteitMapper<LL, LK, G, SG> mapper,
			UwlrMappingContext mappingContext)
	{
		this.mapper = mapper;
		this.mappingContext = mappingContext;
	}

	public LL addLeerling(LeerlingType leerling) throws UwlrEntiteitMappingException
	{
		LL ll = mapper.mapLeerlingType(leerling, mappingContext);
		leerlingen.add(ll);
		return ll;
	}

	public LK addLeerkracht(LeerkrachtType leerkracht) throws UwlrEntiteitMappingException
	{
		LK lk = mapper.mapLeerkrachtType(leerkracht, mappingContext);
		leerkrachten.add(lk);
		return lk;
	}

	public G addGroep(GroepType groep) throws UwlrEntiteitMappingException
	{
		G g = mapper.mapGroepType(groep, mappingContext);
		groepen.add(g);
		return g;
	}

	public SG addSamengesteldeGroep(SamengesteldeGroepType groep)
			throws UwlrEntiteitMappingException

	{
		SG sg = mapper.mapSamengesteldeGroepType(groep, mappingContext);
		samengesteldeGroepen.add(sg);
		return sg;
	}

	public UwlrEntiteitMapper<LL, LK, G, SG> getMapper()
	{
		return mapper;
	}

	public UwlrMappingContext getMappingContext()
	{
		return mappingContext;
	}

}
