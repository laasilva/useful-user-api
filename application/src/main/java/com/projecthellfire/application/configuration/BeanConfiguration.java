package com.projecthellfire.application.configuration;

import com.projecthellfire.core.business.*;
import com.projecthellfire.core.port.adapter.DeleteUserAdapter;
import com.projecthellfire.core.port.adapter.FindUserAdapter;
import com.projecthellfire.core.port.adapter.PersistUserAdapter;
import com.projecthellfire.core.util.Validation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.projecthellfire"})
public class BeanConfiguration {
    @Bean
    Validation validation(FindUserAdapter findUserAdapter) {
        return new Validation(findUserAdapter);
    }
    @Bean
    SaveUser saveUser(PersistUserAdapter persistUserAdapter, Validation validation)  {
        return  new SaveUser(persistUserAdapter, validation);
    }
    @Bean
    Login login(Validation validation) {
        return new Login(validation);
    }

    @Bean
    SearchUser searchUser(FindUserAdapter findUserAdapter) {
        return new SearchUser(findUserAdapter);
    }

    @Bean
    RemoveUser removeUser(DeleteUserAdapter deleteUserAdapter, FindUserAdapter findUserAdapter) {
        return new RemoveUser(deleteUserAdapter, findUserAdapter);
    }

    @Bean
    EditUser editUser(PersistUserAdapter persistUserAdapter, FindUserAdapter findUserAdapter, Validation validation) {
        return new EditUser(persistUserAdapter, findUserAdapter, validation);
    }
}
