package com.zr.service.impl;

import com.zr.bean.News;
import com.zr.bean.NewsQuery;
import com.zr.bean.Tag;
import com.zr.bean.Type;
import com.zr.dao.NewsDao;
import com.zr.service.NewsService;
import com.zr.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDao newsDao;

    @Override
    public Page<News> findAll(Pageable pageable) {
        return newsDao.findAll(pageable);
    }

    @Override
    public News findById(Long id) {
        return newsDao.getOne(id);
    }

    @Override
    public void add(News news) {
       if(news.getId()==null){
           news.setCreateTime(new Date());
           news.setUpdateTime(new Date());
           newsDao.save(news);
       }else {
           News n = newsDao.getOne(news.getId());
           BeanUtils.copyProperties(news,n, MyBeanUtils.getNullPropertyNames(news));
           news.setUpdateTime(new Date());
           newsDao.save(n);
       }

    }

    @Override
    public String findTagIds(List<Tag> tags) {
        //[3,]  [4, ]     3,4
        StringBuffer stringBuffer=new StringBuffer();
        if(!tags.isEmpty()){
            boolean flag=false;
            for(Tag tag:tags){
                if(flag){
                    stringBuffer.append(",");
                    stringBuffer.append(tag.getId());
                }else {
                    stringBuffer.append(tag.getId());
                    flag=true;
                }
            }
        }
        return stringBuffer.toString();
    }

    @Override
    public Page<News> searchNews(Pageable pageable, NewsQuery newsQuery) {
        Specification s=new Specification<News>(){
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates=new ArrayList<>();
                if(!StringUtils.isEmpty(newsQuery.getTitle())){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"),"%"+newsQuery.getTitle()+"%"));
                }
                if(!StringUtils.isEmpty(newsQuery.getTypeId())){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"),newsQuery.getTypeId()));
                }
                if(newsQuery.getRecommend()!=null&& newsQuery.getRecommend()){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommend"),newsQuery.getRecommend()));
                }
                query.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        };
        Page<News> page=newsDao.findAll(s,pageable);
        return page;
    }

    @Override
    public Page<News> findAll(String query, Pageable pageable) {
        return newsDao.findByQuery("%"+query+"%",pageable);
    }

    @Override
    public Page<News> searchNews(Pageable pageable, Long id) {
        return newsDao.findAll(new Specification<News>() {
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Join join=root.join("tags");
                return criteriaBuilder.equal(join.get("id"),id);
            }
        }, pageable);
    }

    @Override
    public List<News> findTop(int i) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0,i,sort);
        return newsDao.findTop(pageable);
    }

    @Override
    public void deleteById(Long id) {
        newsDao.deleteById(id);
    }

    @Override
    public HashMap<String, List<News>> getMap() {
        LinkedHashMap<String, List<News>> map=new LinkedHashMap<>();
        List<String> years=newsDao.findGroupYear();
        for(String y:years){
            List<News> newsList=newsDao.findNewsByYear(y);
            map.put(y,newsList);
        }
        return map;
    }

    @Override
    public Long getCount() {
        return newsDao.count();
    }
}
