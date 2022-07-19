package com.projecthellfire.application.configuration;

import com.projecthellfire.core.business.SaveUser;
import com.projecthellfire.core.business.SearchUser;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.projecthellfire"})
public class BeanConfiguration {
    @Bean
    SaveUser saveUser(PersistUserAdapter persistUserAdapter, FindUserAdapter findUserAdapter)  {
        return  new SaveUser(persistUserAdapter, findUserAdapter);
    }

    @Bean
    SearchUser searchUser(FindUserAdapter findUserAdapter) {
        return new SearchUser(findUserAdapter);
    }
}
