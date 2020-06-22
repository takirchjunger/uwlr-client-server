package nl.topicus.onderwijs.eck2.v2.client;

import nl.topicus.onderwijs.generated.uwlr.v2_0.GroepType;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerkrachtType;
import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlingType;
import nl.topicus.onderwijs.generated.uwlr.v2_0.SamengesteldeGroepType;

public interface UwlrEntiteitMapper<LL, LK, G, SG>
{
	LL mapLeerlingType(LeerlingType leerling, UwlrMappingContext mappingContext)
			throws UwlrEntiteitMappingException;

	LK mapLeerkrachtType(LeerkrachtType leerkracht, UwlrMappingContext mappingContext)
			throws UwlrEntiteitMappingException;

	G mapGroepType(GroepType groep, UwlrMappingContext mappingContext)
			throws UwlrEntiteitMappingException;

	SG mapSamengesteldeGroepType(SamengesteldeGroepType groep, UwlrMappingContext mappingContext)
			throws UwlrEntiteitMappingException;
}

