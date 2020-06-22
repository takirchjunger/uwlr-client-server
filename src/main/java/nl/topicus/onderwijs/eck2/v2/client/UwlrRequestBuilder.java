package nl.topicus.onderwijs.eck2.v2.client;

import nl.topicus.onderwijs.generated.uwlr.v2_0.LeerlinggegevensVerzoek;

public class UwlrRequestBuilder
{
	private String schooljaar;

	private String xsdversie;

	private String brincode;

	private String dependancecode;

	private String schoolkey;

	/**
	 * De velden gegevenssetid en laatstontvangengegevens ondersteunen we verder niet.
	 */

	public UwlrRequestBuilder()
	{
	}

	public UwlrRequestBuilder schooljaar(String schooljaar)
	{
		this.schooljaar = schooljaar;
		return this;
	}

	public UwlrRequestBuilder xsdversie(String xsdversie)
	{
		this.xsdversie = xsdversie;
		return this;
	}

	public UwlrRequestBuilder brincode(String brincode)
	{
		this.brincode = brincode;
		return this;
	}

	public UwlrRequestBuilder dependancecode(String dependancecode)
	{
		this.dependancecode = dependancecode;
		return this;
	}

	public LeerlinggegevensVerzoek build()
	{
		LeerlinggegevensVerzoek request = new LeerlinggegevensVerzoek();
		request.setSchooljaar(schooljaar);
		request.setXsdversie(xsdversie);
		request.setBrincode(brincode);
		request.setDependancecode(dependancecode);
		request.setSchoolkey(schoolkey);
		return request;
	}

}

