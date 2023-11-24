package com.example.demo.repository;

import com.example.demo.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository {
    private final JdbcTemplate jdbcTemplate;  //얘를 써야하는데 얘는 injection(주입) 받을 수 있는 게 아니다. 따라서 아래 @Autowired 처럼 구현
    @Autowired //생성자가 하나만 있으면 @Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource){  //스프링 빈이 dataSource injection 해준다
        jdbcTemplate=new JdbcTemplate(dataSource);
    }
    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert=new SimpleJdbcInsert(jdbcTemplate);  //SimpleJdbcInsert 클래스를 사용하여 JDBC 쿼리를 간단하게 수행하기 위한 객체를 생성
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");  // 삽입할 테이블 이름을 "member"로 지정, 자동 생성되는 키 열은 "id"
        Map<String, Object> parameters = new HashMap<>();  // 회원 정보를 담을 Map을 생성(이름)
        parameters.put("name", member.getName());  // 맵에 회원 이름을 추가. parameters 맵에 "name"이라는 키와 member 객체의 이름(getName() 메서드로 얻은 값)을 추가. 이것은 나중에 SQL 쿼리에서 사용될 매개변수
        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));  // 쿼리 실행 하여 데이터베이스에 회원 정보를 삽입하고, 생성된 키를 반환
        member.setId(key.longValue());   // 회원 객체에 새로 생성된 primary key 설정
        return member;
    }

    @Override //조회 쿼리
    public Optional<Member> findById(Long id) {
        List<Member> result= jdbcTemplate.query("select * from member where id=?", memberRowMapper(),id);// SQL 쿼리를 실행하고 그 결과를 매핑하는 역할. sql 작성 후 결과가 나오는 걸 RowMapper라는 걸로 매핑 해주어야 한다. 실행 결과는 리스트로 반환되기에 list에 저장
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result=jdbcTemplate.query("select * from member where name=?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper(){
        return (rs, rowNum)-> {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return member;
                             };





    }

}
