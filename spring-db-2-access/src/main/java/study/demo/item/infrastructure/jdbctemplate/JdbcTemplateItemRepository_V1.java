package study.demo.item.infrastructure.jdbctemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;
import study.demo.item.domain.dto.ItemSearchCond;
import study.demo.item.domain.dto.ItemUpdateDto;
import study.demo.item.domain.entity.Item;
import study.demo.item.domain.repository.ItemRepository_V1;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
public class JdbcTemplateItemRepository_V1 implements ItemRepository_V1 {
    private final JdbcTemplate template;

    public JdbcTemplateItemRepository_V1(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        String sql = "insert into item(item_name, price, quantity) values (?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(con -> {
            //auto increased id
            PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, item.getItemName());
            ps.setInt(2, item.getPrice());
            ps.setInt(3, item.getQuantity());
            return ps;
        }, keyHolder);

        long key = keyHolder.getKey().longValue();
        item.setId(key);

        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "select id, item_name, price, quantity from item where id = ?";
        try {
            Item item = template.queryForObject(sql, itemRowMapper(), id);
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();

        }
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.itemName();
        Integer maxPrice = cond.maxPrice();
        String sql = "select id, item_name, price, quantity from item";

        //동적 쿼리
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }

        boolean andFlag = false;
        List<Object> param = new ArrayList<>();
        if (StringUtils.hasText(itemName)) {
            sql += " item_name like concat('%',?,'%')";
            param.add(itemName);
            andFlag = true;
        }

        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= ?";
            param.add(maxPrice);
        }

        log.info("sql={}", sql);

        return template.query(sql, itemRowMapper(), param.toArray());
    }

    @Override
    public void update(Long id, ItemUpdateDto dto) {
        String sql = "update item set item_name = ?, price = ?, quantity = ? where id = ?";
        template.update(sql, dto.itemName(), dto.price(), dto.quantity(), id);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from item where id = ?";
        template.update(sql, id);
    }

    private RowMapper<Item> itemRowMapper() {
        return ((rs, rowNum) -> {
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setItemName(rs.getString("item_name"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            return item;
        });
    }
}
