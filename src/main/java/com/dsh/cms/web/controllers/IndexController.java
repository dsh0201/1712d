package com.dsh.cms.web.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsh.cms.dao.ArticleRespository;
import com.dsh.cms.domain.Article;
import com.dsh.cms.domain.Category;
import com.dsh.cms.domain.Channel;
import com.dsh.cms.domain.Collect;
import com.dsh.cms.domain.Comment;
import com.dsh.cms.domain.User;
import com.dsh.cms.enums.ArticleEnum;
import com.dsh.cms.service.ArticleService;
import com.dsh.cms.service.CategoryService;
import com.dsh.cms.service.ChannelService;
import com.dsh.cms.service.CollectService;
import com.dsh.cms.service.CommentService;
import com.dsh.cms.service.SlideService;
import com.dsh.cms.service.UserService;
import com.dsh.cms.util.CMSException;
import com.dsh.cms.util.CMSResult;
import com.dsh.cms.util.HLUtils;
import com.dsh.cms.vo.ArticlePicVO;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.thyu.common.utils.RandomUtil;

/**
 * 
 * @ClassName: IndexController
 * @Description: cms首页控制器
 * @date 2020年4月7日
 *
 */
@Controller
public class IndexController {
	@Autowired
	private ChannelService channelService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private SlideService slideService;
	@Autowired
	private UserService userService;
	@Autowired
	private CollectService collectService;
	@Autowired
	private CommentService commentService;

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	ArticleRespository articleRespository;

	@Autowired
	ElasticsearchTemplate elasticsearchTemplate;

	/**
	 * 实现从es搜索的方法
	 * 
	 */
	@RequestMapping("/search")
	public String search(String key, Model model, @RequestParam(defaultValue = "1") int pageNum,
			@RequestParam(defaultValue = "5") int pageSize) {
		// 需求:从es中查询和key关联的数据
		// List<Article> list = articleRespository.findByTitleOrContent(key,key);
		// System.out.println(list);
		long start = System.currentTimeMillis();
		// 使用工具类实现高亮
		PageInfo<Article> info = (PageInfo<Article>) HLUtils.findByHighLight(elasticsearchTemplate, Article.class,
				pageNum, pageSize, new String[] { "title" }, "id", key);
		long end = System.currentTimeMillis();
		System.err.println("es搜索耗时:" + (end - start) + "毫秒");
		model.addAttribute("key", key);
		model.addAttribute("page", info);
		model.addAttribute("articleList", info.getList());
		return "index/index";
	}

	/**
	 * 
	 * @Title: index @Description: 进入首页 @param @return 参数 @return String
	 * 返回类型 @throws
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = { "", "/", "index" })
	public String index(Model model, Article article, @RequestParam(defaultValue = "1") Integer pageNum,
			@RequestParam(defaultValue = "4") Integer pageSize) {
		// 查询条件放回作用域
		model.addAttribute("article", article);
		// 1.先从redis中查询数据
		long start = System.currentTimeMillis();
		List<Channel> redisChannel = redisTemplate.opsForList().range("channels", 0, -1);
		long end = System.currentTimeMillis();
		System.err.println("从redis查询耗时:" + (end - start) + "毫秒");
		// 2.判断redis中有无数据,如果有,说明不是第一次访问,如果没有说明是第一次
		if (redisChannel == null || redisChannel.size() == 0) {
			// 3.是第一次---->从mysql中查询
			// 从mysql中查询所有的栏目
			long s = System.currentTimeMillis();
			List<Channel> channelList = channelService.getChannelList();
			long e = System.currentTimeMillis();
			System.err.println("从mysql中查询耗时:" + (e - s) + "毫秒");
			System.err.println("从mysql中查询了栏目....");
			// 保存到redis一份,添加到域对象
			redisTemplate.opsForList().rightPushAll("channels", channelList.toArray());
			System.err.println("往redis里保存了栏目信息....");
			model.addAttribute("channelList", channelList);

		} else {
			// 4.不是第一次--->从redis中直接查询
			System.err.println("从redis中查询了栏目信息....");
			model.addAttribute("channelList", redisChannel);
		}
		setAvailable(article);

		// 如果栏目不为空，则查询栏目下的分类
		if (null != article.getChannelId()) {
			// 根据栏目id查询分类
			List<Category> categoryList = categoryService.getCategoriesByChannelId(article.getChannelId());
			model.addAttribute("categoryList", categoryList);
			// 查询该栏目下的所有文章或分类下的所有文章
			PageInfo<Article> page = articleService.getArticleList(article, pageNum, pageSize);
			model.addAttribute("page", page);
			model.addAttribute("articleList", page.getList());
		} else {
			// 查询热点文章
			Article hotArticle = new Article();
			// 查询的文章应为已发布，未删除，html类型
			setAvailable(hotArticle);
			hotArticle.setHot(1);
			PageInfo<Article> page = articleService.getArticleList(hotArticle, pageNum, pageSize);
			model.addAttribute("page", page);
			model.addAttribute("articleList", page.getList());
			// 查询广告
			model.addAttribute("slideList", slideService.getSlideList());
		}

		// 右侧显示当前栏目的最新五篇文章,如果是首页面则显示所有文章的近5篇
		Article recentArticle = new Article();
		setAvailable(recentArticle);
		if (null != article.getChannelId()) {
			recentArticle.setChannelId(article.getChannelId());
			// 查询栏目名称
			model.addAttribute("channelName", channelService.getChannelById(article.getChannelId()).getName());
		}

		//////////////////////////////// 优化最新文章
		// 1.先看redis中又没有,主要是为了确定是不是第一次访问
		List<Article> redisNewArticles = (List<Article>) redisTemplate.opsForValue().get("new_articles");
		// 2.如果redis中没有数据,说明是第一次访问
		if (redisNewArticles == null || redisNewArticles.size() == 0) {
			// 就是从mysql中查询最新的5篇文章
			PageInfo<Article> recentArticles = articleService.getArticleList(recentArticle, 1, 5);
			// 并且往redis中存一份
			redisTemplate.opsForValue().set("new_articles", recentArticles.getList()/* ,5,TimeUnit.MINUTES */);
			// 为我们的key设置一个过期时间,时间到,redis的数据就被清空
			redisTemplate.expire("new_articles", 5, TimeUnit.MINUTES);
			System.err.println("从mysql中查询了最新文章,并且保存到了redis...");
			model.addAttribute("recentArticles", recentArticles.getList());
		} else {
			// 3.如果redis中有,说明不是第一次访问,
			System.err.println("从redis中查询了最新文章...");
			model.addAttribute("recentArticles", redisNewArticles);
		}

		/////////////////////////////////// 优化最新文章 end
		// 查询最近24小时的热门文章，并在里面随机四篇
		Article recent24HourHotArticle = new Article();
		setAvailable(recent24HourHotArticle);
		recent24HourHotArticle.setHot(1);
		// 先查询出所有热点文章
		List<Article> hotArticles = articleService.getArticleList(recent24HourHotArticle, 1, Integer.MAX_VALUE)
				.getList();
		// 选取最近24小时的
		Calendar limit = Calendar.getInstance();
		limit.add(Calendar.HOUR, -24);
		Date limitTime = limit.getTime();
		List<Article> hot24Articles = hotArticles.stream()
				.filter((e) -> (e.getCreated().getTime() > limitTime.getTime())).collect(Collectors.toList());
		// 要显示四条，满足条件的文章数小于等于4的话，直接全部返回，否则从中随机抽取四篇
		int size = hot24Articles.size();
		if (size > 4) {
			int[] random = RandomUtil.subRandom(0, size - 1, 4);
			// 对数组排序，保证结果是按照xml中默认的时间排序
			Arrays.sort(random);
			List<Article> articles24 = new ArrayList<Article>();
			for (int i = 0; i < random.length; i++) {
				articles24.add(hot24Articles.get(random[i]));
			}
			model.addAttribute("hot24Articles", articles24);
		} else {
			model.addAttribute("hot24Articles", hot24Articles);
		}
		return "index/index";
	}

	/**
	 * 
	 * @Title: setAvailable @Description: 设置首页板块文章的查询条件 @param @param article
	 * 参数 @return void 返回类型 @throws
	 */
	private void setAvailable(Article article) {
		// 查询的文章应为已发布，未删除，html类型
		article.setStatus(1);
		article.setDeleted(0);
	}

	/**
	 * 
	 * @Title: detail @Description: 跳转到文章详情页 @param @param id @param @return
	 * 参数 @return String 返回类型 @throws
	 */

	// 注入spring的线程池核心类
	@Autowired
	ThreadPoolTaskExecutor executor;
	
	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@SuppressWarnings("unchecked")
	@RequestMapping("detail")
	public String detail(HttpServletRequest req, Model model, Integer id, HttpSession session,
			@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "5") Integer pageSize) {

		// 查询文章
		Article article = articleService.getById(id);
		//==============================使用kafka=========================
		/**
		 * ，每访问一次就同时往文章表的浏览量字段加1，如果一篇文章集中一时刻上百万次浏览，
		 * 就会给数据库造成压力。现在请你利用Kafka进行流量削峰。
		 * 当用户浏览文章时，往Kafka发送文章ID，在消费端获取文章ID，再执行数据库加1操作。
		 */
		//往kafka发送文章id
		kafkaTemplate.send("articles", "hits="+id);
		System.err.println("发送到kfaka成功!!");
		
		
		
		
		
		
//////////////////////////////使用redis数据库访问量+1/////////////////////////////
		/*// 获取用户ip地址
		String ip = req.getRemoteAddr();
		// 拼装redis的key
		String key = "Hits_" + id + "_" + ip;

		*//**
		 * 当用户浏览文章时，将“Hits_${文章ID}_${用户IP地址}”为key，查询Redis里有没有该key，
		 * 如果有key，则不做任何操作。如果没有，则使用Spring线程池异步执行数据库加1操作，
		 * 并往Redis保存key为Hits_${文章ID}_${用户IP地址}，value为空值的记录，而且有效时长为5分钟。
		 *//*
		Boolean b = redisTemplate.hasKey(key);
		if (!b) {
			// 如果没有该key,则使用Spring线程池异步执行数据库加1操作(需要在spring的配置里配置线程池)
			executor.execute(new Runnable() {

				@Override
				public void run() {
					// 一个新的线程
					// 数据库+1
					Integer hits = article.getHits();
					article.setHits(hits+1);
					//同步到数据库(保存到数据库)
					articleService.update(article);
					System.err.println("文章点击量已经+1");
//					并往Redis保存key为Hits_${文章ID}_${用户IP地址}，value为空值的记录，而且有效时长为5分钟。
					redisTemplate.opsForValue().set(key, "",5,TimeUnit.MINUTES);
				}
			});
		}
*/
		
		//////////////////////////////////////////////////////////////////////
		model.addAttribute("article", article);
		// 获取文章的userId
		Integer userId = article.getUserId();
		// 根据userId获取这个作者的所有文章
		Article queryArticle = new Article();
		setAvailable(queryArticle);
		queryArticle.setUserId(userId);
		List<Article> userArticleList = articleService.getArticleList(queryArticle, 1, Integer.MAX_VALUE).getList();
		// 过滤，选出这个作者的最近三篇文章(不包括当前这篇)
		List<Article> articleList = userArticleList.stream().filter(e -> (e.getId() != id)).limit(3)
				.collect(Collectors.toList());
		model.addAttribute("articleList", articleList);
		// 获取作者信息放入作用域
		User user = userService.getUserById(userId);
		model.addAttribute("user", user);
		// 查询当前文章是否已被收藏
		User currentUser = (User) session.getAttribute("user");
		if (null != currentUser && collectService.isCollected(currentUser.getId(), id)) {
			model.addAttribute("isCollected", true);
		} else {
			model.addAttribute("isCollected", false);
		}
		// 查询当前文章的评论内容
		PageInfo<Comment> page = commentService.getCommentsByArticleId(id, pageNum, pageSize);
		model.addAttribute("page", page);
		model.addAttribute("commentList", page.getList());
		// 如果当前文章是图文类文章，需要把图文信息解析出来放到作用域中
		if (article.getContentType() == ArticleEnum.PIC.getCode()) {
			String content = article.getContent();
			List<ArticlePicVO> pics = new ArrayList<ArticlePicVO>();
			Gson gson = new Gson();
			JsonArray array = new JsonParser().parse(content).getAsJsonArray();
			for (JsonElement element : array) {
				pics.add(gson.fromJson(element, ArticlePicVO.class));
			}
			model.addAttribute("pics", pics);
			System.out.println(pics);
		}
		return "index/article";
	}

	/**
	 * 
	 * @Title: disCollect @Description: 取消收藏 @param @param userId @param @param
	 * articleId @param @return 参数 @return boolean 返回类型 @throws
	 */
	@RequestMapping("discollect")
	@ResponseBody
	public boolean disCollect(Integer userId, Integer articleId) {
		return collectService.deleteCollectByUAId(userId, articleId);
	}

	/**
	 * 
	 * @Title: disCollect @Description: 收藏文章 @param @param collect @param @return
	 * 参数 @return boolean 返回类型 @throws
	 */
	@RequestMapping("collect")
	@ResponseBody
	public CMSResult<Collect> collect(Collect collect, HttpSession session) {
		CMSResult<Collect> result = new CMSResult<Collect>();

		try {
			if (null == session.getAttribute("user")) {
				throw new CMSException("请登录");
			}
			collect.setCreated(new Date());
			collectService.insert(collect);
			result.setCode(200);
		} catch (CMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(505);
			result.setMsg("未知错误!");
		}
		return result;
	}

	/**
	 * 
	 * @Title: comment @Description: 发表评论 @param @param comment @param @param
	 * session @param @return 参数 @return CMSResult<Comment> 返回类型 @throws
	 */
	@RequestMapping("comment")
	@ResponseBody
	public CMSResult<Comment> comment(Comment comment, HttpSession session) {
		CMSResult<Comment> result = new CMSResult<Comment>();
		try {
			if (null == session.getAttribute("user")) {
				throw new CMSException("请登录");
			}
			comment.setCreated(new Date());
			commentService.insert(comment);
			result.setCode(200);
		} catch (CMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setCode(500);
			result.setMsg(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setCode(505);
			result.setMsg("未知错误!");
		}
		return result;
	}
}
