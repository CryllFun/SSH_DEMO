package com.iotek.test;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.iotek.bean.User;

public class HibernateTest {

	//sessionFactory（可以理解为数据库连接池）是生产session（理解为Connection）的工厂
	//一般一个应用只初始化一个sessionfactory对象，创建sessionfactory时候很消耗资源
	//是线程安全的
	//一旦被创建，则赋予特定的配置信息
	private SessionFactory sessionFactory = null;
	private Session session = null;
	//事务
	private Transaction tx = null;

//	持久化对象：
//	必须要有一个无参的构造方法：因为Hibernate要使用反射去实例化持久化类
//	必须提供一个标识属性：没有该属性，一些功能不起作用
//	必须是非final类：如果是final类，则无法使用CGLIB代理
//	必须要提供set和get方法： JavaBean风格的属性实现持久化（pojo）
//	当把持久化类的实例放入到set集合（进行关联映射），必须重写equals和hashCode方法
	
//	尽量让hibernate去维护主键，主键的生成策略改成native
	
	@Before
	public void init() {
		//专门用于负责管理hibernate配置信息的
		//hibernate运行的底层信息：配置数据库基本信息、数据库方言，数据库生成策略, c3p0数据库连接池等等
		//持久化类与数据库表的映射关系文件（*.hbm.xml）
		Configuration config = new Configuration().configure("/hibernate.cfg.xml");
		ServiceRegistry bsr = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		sessionFactory = config.buildSessionFactory(bsr);
		//根据session工厂生产session
		session = sessionFactory.openSession();
		//开启一个事务
		tx = session.beginTransaction();
	}

	@Test
	public void testDelete() {
		// 1. 手动new对象
//		User user=new User();
//		user.setUserId(3);
//		session.delete(user);

		// 2. 先查询再删除
		User user = (User) session.get(User.class, 2);
		session.delete(user);
	}

	@Test
	public void testUpdate() {
		// 1. 手动 new 对象, 不建议
//		User user = new User();
//		user.setUserId(3);
//		user.setUserName("zhaoliuxxx");
//		user.setUserPwd("654321xxx");
//		user.setEmail("xxx@qq.comxx");
//		user.setMobile("99999999xx");
//		session.update(user);

		// 2. 先查询再修改（推荐）
		User user = (User) session.get(User.class, 3);
		user.setUserName("qianqi");
		session.update(user);
	}

	@Test
	public void testSave() {
		User user = new User();
//		user.setUserId(100);
		user.setUserName("zhaoliu");
		user.setUserPwd("654321");
		user.setEmail("xxx@qq.com");
		user.setMobile("99999999");
		user.setCreateTime(new Date());
//		session.save(user);
		session.persist(user);
		
	}

	@Test
	public void test() {
//		User user = (User)session.get(User.class, 10);
		User user = (User) session.load(User.class, 10);
		System.out.println("---------");
		System.out.println(user);
	}

//	SQL: HQL
	@Test
	public void testHQL() {
//		String sql="select * from t_user";
//		String hql = "from User where userId=?";
//		Query query = session.createQuery(hql);
//		query.setParameter(0, 1);
//		List list = query.list();
//		List list = session.createQuery(hql).setParameter(0, 1).list();
//		for (Object obj : list) {
//			System.out.println(obj);
//		}

		String hql = "from User where userId=? and userName=?";
		User user = (User) session.createQuery(hql)
				.setParameter(0, 1).setParameter(1, "zhangsan")
				.list().get(0);
		System.out.println(user);
	}

	@After
	public void close() {
		//提交事务
		tx.commit();
		//关闭session
		session.close();
		//关闭session工厂
		sessionFactory.close();
	}
}
