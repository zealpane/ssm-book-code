package biz.item.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 告警
 * </p>
 *
 * @author author
 * @since 2019-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("g_item_alarm")
@ApiModel(value="ItemAlarm对象", description="告警")
public class ItemAlarm extends Model<ItemAlarm> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "告警类型")
    private Integer type;

    @ApiModelProperty(value = "告警值")
    private Double val;

    @ApiModelProperty(value = "设备id")
    private Integer deviceId;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
