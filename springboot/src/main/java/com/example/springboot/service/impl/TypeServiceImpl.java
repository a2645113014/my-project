package com.example.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;  // 添加这一行
import com.example.springboot.entity.Type;
import com.example.springboot.mapper.TypeMapper;
import com.example.springboot.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;  // 添加这一行

@Service
public class TypeServiceImpl implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public void save(Type type) {
        typeMapper.insert(type);
    }

    @Override
    public void update(Type type) {
        typeMapper.updateById(type);
    }

    @Override
    public void remove(Integer id) {
        typeMapper.deleteById(id);
    }

    @Override
    public List<Type> selectAll() {  // 修改返回类型为 List<Type>
        return typeMapper.selectList(null);
    }

    @Override
    public Type selectById(Integer id) {
        return typeMapper.selectById(id);
    }

    @Override
    public IPage<Type> selectPage(Integer pageNum, Integer pageSize, String name) {
        Page<Type> page = new Page<>(pageNum, pageSize);  // 现在 Page 不会标红了
        LambdaQueryWrapper<Type> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Type::getName, name);
        return typeMapper.selectPage(page, queryWrapper);
    }
}