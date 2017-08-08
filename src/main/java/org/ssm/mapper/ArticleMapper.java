package org.ssm.mapper;

import org.apache.ibatis.annotations.Select;
import org.ssm.until.TbArticle;

import java.util.List;

public interface ArticleMapper {
    @Select("SELECT * FROM tb_article WHERE id IN " +
            "(SELECT article_id FROM tb_item WHERE order_id = #{id})")
    List<TbArticle> selectByOrderId(Integer id);
}
