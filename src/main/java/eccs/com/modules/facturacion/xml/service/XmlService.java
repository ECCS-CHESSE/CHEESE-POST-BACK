package eccs.com.modules.facturacion.xml.service;

import eccs.com.core.dtos.ResponseDto;
import eccs.com.modules.facturacion.xml.dto.FacturasTimbradasRequestDto;
import eccs.com.modules.facturacion.xml.dto.XmlRequestDto;

public interface XmlService {
    ResponseDto<Object> getXml(XmlRequestDto request);
    ResponseDto<Object> getFacturasTimbradas(FacturasTimbradasRequestDto request);
}
