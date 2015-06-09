package com.mb.service.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mb.beans.common.Message;
import com.mb.beans.sql.User;
import com.mb.common.persistence.sql.ISqlDao;
import com.mb.common.persistence.sql.impl.SqlDao;
import com.mb.common.util.JsonUtil;
import com.mb.service.IUserService;

/**
 * 用户服务
 * 本来打算用mybatis，但是尼玛现在就一张表
 * 的sql就先写死在service里面吧，以后表多了就改成mybatis的
 * @author lordtan
 * @date 2015年5月24日
 */
@Service
public class UserService implements IUserService {
	
	@Resource(name="sqlDao")
	private ISqlDao sqldao; 

	public Message login(String user) {
		User u = JsonUtil.json2obj(user, User.class);
		String sql = "select * from t_user where account=? and password=? ";
		List<User> users = sqldao.query(sql, new Object []{u.getAccount(),u.getPassword()}, User.class);
		Message mesg = new Message();
		if(null!=users && users.size()>0){
			String updateSql = " update t_user set loginDate=? where account=? and password=? ";
			sqldao.update(updateSql, new Object [] {new Date().getTime(),u.getAccount(),u.getPassword()}); //更新最后登录时间
			mesg.setData(JsonUtil.obj2json(users.get(0))); //返回用户登陆信息
			mesg.setState(Message.SUCCESS);
			mesg.setMsg("登陆成功");
		}else{
			/**
			 * 登陆失败有两种可能，账号不存在，密码错误
			 */
			mesg.setState(Message.FAILED);
			String sqlCheck = "select * from t_user where account=?";
			List<User> usersCheck = sqldao.query(sqlCheck, new Object []{u.getAccount()}, User.class);
			if(null!=usersCheck && usersCheck.size()>0){  //密码错误
				mesg.setMsg("密码错误");
			}else{//账号不存在
				mesg.setMsg("账号不存在");
			}
		}
		return mesg;
	}
    
	public Message register(String user) {
		User u = JsonUtil.json2obj(user, User.class);
		Message mesg = Message.getInstance();
		//校验用户名是否存在
		String sqlCheck = "select * from t_user where account=?";
		List<User> usersCheck = sqldao.query(sqlCheck, new Object []{u.getAccount()}, User.class);
		if(null!=usersCheck && usersCheck.size()>0){  //账号已存在
			mesg.setMsg("账号已存在");
			mesg.setState(Message.FAILED);
			return mesg;
		}
		
		u.setId(UUID.randomUUID().toString().replace("-", ""));
		u.setCreatedDate(new Date().getTime());
		u.setIsActive(1);
		sqldao.insert(u);
		mesg.setData(u.toString());
		return mesg.setState(Message.SUCCESS).setMsg(u.getId());
	}

	
	public String modify(String property, String value) {
//		String sql = "update"
		return null;
	}

}
