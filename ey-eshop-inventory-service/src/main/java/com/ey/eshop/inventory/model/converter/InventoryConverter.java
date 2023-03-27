package com.ey.eshop.inventory.model.converter;

import com.ey.eshop.inventory.model.dto.InventoryDto;
import com.ey.eshop.inventory.model.entity.InventoryDo;
import com.ey.eshop.inventory.model.vo.InventoryVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface InventoryConverter {

    InventoryConverter INSTANCE = Mappers.getMapper(InventoryConverter.class);

    List<InventoryDto> inventoryDosToInventoryDtos(List<InventoryDo> inventoryDo);

    List<InventoryVo> inventoryDtosToInventoryVs(List<InventoryDto> inventoryDto);
}
