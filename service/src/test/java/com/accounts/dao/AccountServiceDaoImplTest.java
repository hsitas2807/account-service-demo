package com.accounts.dao;

import com.accounts.dto.Account;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountServiceDaoImplTest {

    @Mock
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    AccountServiceDaoImpl accountServiceDao;


    @Mock
    private BasicDataSource dataSource;


    @Before
    public void setup() throws Exception {
        dataSource = mock(BasicDataSource.class);
        accountServiceDao=new AccountServiceDaoImpl(dataSource);
        namedParameterJdbcTemplate=mock(NamedParameterJdbcTemplate.class);
        accountServiceDao.setNamedParameterJdbcTemplate(namedParameterJdbcTemplate);

    }
    //TO-DO




}
