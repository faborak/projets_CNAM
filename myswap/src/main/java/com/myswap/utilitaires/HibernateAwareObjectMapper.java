package com.myswap.utilitaires;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

/**
 * 
 * @author fabo
 * Permits to MappingJackson2HttpMessageConverter to use an ObjectMapper which uses Hibernate4Module.
 * Avoid LazyInitialisation problem when converting to Json.
 *
 */
public class HibernateAwareObjectMapper extends ObjectMapper {
 

    public HibernateAwareObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        registerModule(hm);
    }
}