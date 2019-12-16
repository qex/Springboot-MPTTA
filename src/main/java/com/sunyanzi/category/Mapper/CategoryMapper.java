package com.sunyanzi.category.Mapper;

import com.sunyanzi.category.Entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Select("SELECT `cid`, `name` FROM `category`  ORDER BY `left`")
    List<Category> getAll();

    @Select(
        "SELECT `c1`.`cid`, `c1`.`name` FROM `category` AS `c1` INNER JOIN `category` AS `c2` ON `c2`.`cid`=#{cid} " +
        "WHERE `c1`.`left` BETWEEN `c2`.`left` AND `c2`.`right` ORDER BY `c1`.`left`"
    )
    List<Category> getChild(short cid);

    @Select(
        "SELECT `c1`.`cid`, `c1`.`left`, `c1`.`name`, COUNT( `c2`.`name` ) - 1 AS `depth` FROM `category` " +
        "AS `c1` INNER JOIN `category` AS `c2` ON `c1`.`left` BETWEEN `c2`.`left` AND `c2`.`right` " +
        "GROUP BY `c1`.`cid`, `c1`.`left`, `c1`.`name` ORDER BY `c1`.`left`"
    )
    List<Category> calculateDepth();

    @Select("SELECT `left`, `right`, `right` - `left` + 1 AS `width` FROM `category` WHERE `cid` = #{cid} LIMIT 1")
    Category getPosition(short cid);

    @Select("SELECT `left` FROM `category` WHERE `cid` = #{cid} LIMIT 1")
    short getLeft(short cid);

    @Select("SELECT `right` FROM `category` WHERE `cid` = #{cid} LIMIT 1")
    short getRight(short cid);

    @Insert(
        "INSERT INTO `category` ( `left`, `right`, `name` ) SELECT " +
        "MAX(`right`) + 1, MAX(`right`) + 2, #{name} FROM `category`"
    )
    @Options(useGeneratedKeys = true, keyProperty = "cid")
    void createRoot(Category c);

    @Insert(
        "INSERT INTO `category` ( `left`, `right`, `name` ) VALUES ( #{v} + 1, #{v} + 2, #{c.name} )"
    )
    @Options(useGeneratedKeys = true, keyProperty = "c.cid")
    void createNode(Category c, short v);

    @Update("UPDATE `category` SET `name`=#{c.name} WHERE `cid`=#{cid} LIMIT 1")
    void updateNode(Category c, short cid);

    @Delete("DELETE FROM `category` WHERE `left` BETWEEN #{left} AND #{right}")
    void deleteNode(short left, short right);

    @Update("UPDATE `category` SET `left` = `left` + 2 WHERE `left` > #{left}")
    void expandLeft(short left);

    @Update("UPDATE `category` SET `right` = `right` + 2 WHERE `right` > #{right}")
    void expandRight(short right);

    @Update("UPDATE `category` SET `left` = `left` - #{width} WHERE `left` > #{left}")
    void shrinkLeft(short left, short width);

    @Update("UPDATE `category` SET `right` = `right` - #{width} WHERE `right` > #{right}")
    void shrinkRight(short right, short width);

}
