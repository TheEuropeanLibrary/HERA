package org.theeuropeanlibrary.hera.rest.administration.objectmapper;
import org.theeuropeanlibrary.maia.common.definitions.Provider;
import org.theeuropeanlibrary.maia.converter.json.EntityJsonDeserializer;
import org.theeuropeanlibrary.maia.converter.json.EntityJsonSerializer;
import org.theeuropeanlibrary.maia.converter.json.ProviderEntityJsonDeserializer;
import org.theeuropeanlibrary.maia.converter.json.factory.BaseJsonConverterFactory;
import org.theeuropeanlibrary.maia.tel.model.provider.ProviderRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;


//TODO: needs to be moved to separate project (+ update hera-administration-context.xml)
public class TelObjectMapper extends ObjectMapper {
	
	public TelObjectMapper() {
		
        SimpleModule module = new SimpleModule("TelModule");

        EntityJsonSerializer<Provider> serializer = new EntityJsonSerializer<>(new BaseJsonConverterFactory(ProviderRegistry.INSTANCE));
        EntityJsonDeserializer<Provider> deserializer = new ProviderEntityJsonDeserializer(new BaseJsonConverterFactory(ProviderRegistry.INSTANCE));
        
        module.addSerializer(Provider.class, serializer);
        module.addDeserializer(Provider.class, deserializer);
        
        this.registerModule(module);
    }
 
}
