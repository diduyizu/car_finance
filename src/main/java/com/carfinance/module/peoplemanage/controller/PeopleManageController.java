package com.carfinance.module.peoplemanage.controller;

import com.carfinance.module.common.domain.Menu;
import com.carfinance.module.common.domain.Org;
import com.carfinance.module.common.domain.Role;
import com.carfinance.module.common.service.CommonService;
import com.carfinance.module.init.service.InitService;
import com.carfinance.module.login.domain.User;
import com.carfinance.module.peoplemanage.domain.OrgUserRole;
import com.carfinance.module.peoplemanage.service.PeopleManageService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Controller
@RequestMapping("/people")
public class PeopleManageController {
	final Logger logger = LoggerFactory.getLogger(PeopleManageController.class);
	
	@Autowired
	private CommonService commonService;
    @Autowired
    private PeopleManageService peopleManageService;
    @Autowired
    private InitService initService;
	@Autowired
	private Properties appProps;

    /**
     * 人员管理-角色管理
     * @param model
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/role/index" , method = {RequestMethod.GET , RequestMethod.POST})
	public String roleIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");

        List<Role> roleList = new ArrayList<Role>();
        String role_name = request.getParameter("role_name");
        if(role_name == null || "".equals(role_name.trim())) {
            roleList = initService.getRole();
        } else {
            Role role = this.peopleManageService.roleQuery(role_name);
            roleList.add(role);
        }

        model.addAttribute("role_name" , role_name);
        model.addAttribute("roleList" , roleList);
		return "/module/peoplemanage/role/index";
	}

    /**
     * 人员管理-人员管理
     * @param model
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/people/index" , method = RequestMethod.GET)
	public String peopleIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("people.people.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String org_id_str = request.getParameter("org_id_str");
        List<Org> user_org_list = this.commonService.getUserOrgList(user.getUser_id());//获取用户所在组织
        long org_id = (org_id_str == null || "".equals(org_id_str.trim())) ? user_org_list.get(0).getOrg_id() : Long.valueOf(org_id_str);
        Map<String , Object> map = this.peopleManageService.getOrgUserlist(org_id , start , size);//获取某一组织用户列表以及用户总数
        long total = (Long)map.get("total");;
        List<User> user_list = (List<User>)map.get("org_user_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);

        model.addAttribute("choose_org_id" , org_id);
        model.addAttribute("user_org_list" , user_org_list);
        model.addAttribute("user_list" , user_list);
        return "/module/peoplemanage/people/index";
	}

    /**
     * 人员管理-人员管理
     * 点击“新增用户”，进入新增用户页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/people/add" , method = RequestMethod.GET)
    public String peopleAddIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String org_id = request.getParameter("org_id");//当前所在组织id
        Org org = this.peopleManageService.getOrgByOrgId(Long.valueOf(org_id));
        List<Role> roleList = initService.getRole();

        model.addAttribute("org" , org);
        model.addAttribute("role_list" , roleList);
        return "/module/peoplemanage/people/add";
    }

    /**
     * 人员管理-人员管理-新增用户－保存
     * 进行用户录入操作
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/people/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public int peopleDoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String login_name = request.getParameter("login_name");
        String login_pwd = request.getParameter("login_pwd");
        String user_name = request.getParameter("user_name");
        String nick_name = request.getParameter("nick_name");

        String org_id = request.getParameter("org_id");
        String role_id = request.getParameter("role_id");

        return this.peopleManageService.peopleDoAdd(user.getUser_id(), login_name, login_pwd, user_name, nick_name , org_id , role_id);
    }

    /**
     * 人员管理-人员管理
     * 点击“编辑”，进入编辑用户页面
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/people/edit" , method = RequestMethod.GET)
    public String peopleEditIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String edited_user_id = request.getParameter("edited_user_id");//被编辑的用户id
        User edited_user = commonService.getUserById(Long.valueOf(edited_user_id));
        model.addAttribute("edited_user" , edited_user);

        return "/module/peoplemanage/people/edit";
    }

    /**
     * 人员管理-人员管理-编辑－保存
     * 进行用户编辑操作
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/people/doedit" , method = RequestMethod.POST)
    @ResponseBody
    public int peopleDoEdit(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String edited_user_id = request.getParameter("edited_user_id");//被编辑的用户id
        String real_name = request.getParameter("real_name");//被编辑的用户真实姓名

        return this.peopleManageService.peopleDoEdit(Long.valueOf(edited_user_id), real_name, user.getUser_id());
    }

    /**
     * 执行删除操作
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/people/dodelete" , method = RequestMethod.POST)
    @ResponseBody
    public int peopleDoDelete(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String edited_user_id = request.getParameter("edited_user_id");//被编辑的用户id
        return this.peopleManageService.peopleDoDelete(Long.valueOf(edited_user_id), user.getUser_id());
    }

    /**
     * 人员管理-人员角色管理
     * @param model
     * @param request
     * @param response
     * @return
     */
	@RequestMapping(value = "/peoplerole/index" , method = RequestMethod.GET)
	public String peopleroleIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
		User user = (User)request.getSession().getAttribute("user");

        String pageindexStr = request.getParameter("page_index");//第几页
        int page_index = Integer.parseInt(StringUtils.isBlank(pageindexStr) || "0".equals(pageindexStr) ? "1" : pageindexStr);
        int size = Integer.valueOf(appProps.get("people.people.size").toString());//每页显示条数
        int start = (page_index - 1) * size;

        String org_id_str = request.getParameter("org_id_str");
        List<Org> user_org_list = this.commonService.getUserOrgList(user.getUser_id());
        long org_id = (org_id_str == null || "".equals(org_id_str.trim())) ? user_org_list.get(0).getOrg_id() : Long.valueOf(org_id_str);
        Map<String , Object> map = this.peopleManageService.getOrgUserRolelist(org_id, start, size);
        long total = (Long)map.get("total");;
        List<OrgUserRole> org_user_role_list = (List<OrgUserRole>)map.get("org_user_role_list");

        long temp = (total - 1) <= 0 ? 0 : (total - 1);
        int pages = Integer.parseInt(Long.toString(temp / size)) + 1;
        int prepages = (page_index - 1) <= 0 ? 1 : (page_index - 1);
        int nextpages = (page_index + 1) >= pages ? pages : (page_index + 1);

        model.addAttribute("current_page" , page_index);
        model.addAttribute("pages" , pages);
        model.addAttribute("prepage" , prepages);
        model.addAttribute("nextpage" , nextpages);
        model.addAttribute("choose_org_id" , org_id);
        model.addAttribute("user_org_list" , user_org_list);
        model.addAttribute("org_user_role_list" , org_user_role_list);
        return "/module/peoplemanage/peoplerole/index";
	}

    /**
     * 人员管理-人员角色管理
     * 执行“新增”
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peoplerole/doadd" , method = RequestMethod.POST)
    @ResponseBody
    public String peopleroleDoAdd(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        return "/module/peoplemanage/peoplerole/edit";
    }

    /**
     * 人员管理-人员角色管理
     * 点击“删除”
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/peoplerole/doedit" , method = RequestMethod.GET)
    @ResponseBody
    public String peopleroleDodelete(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        String delete_user_id = request.getParameter("delete_user_id");//被删除的用户id
        String role_id = request.getParameter("role_id");//被删除的用户的角色

        return "/module/peoplemanage/peoplerole/edit";
    }

    /**
     * 角色权限配置
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/rolemenu/index" , method = RequestMethod.GET)
    public String roleMenuIndex(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");

        String role_id_str = request.getParameter("choose_role_id");
        long role_id = (role_id_str == null || "".equals(role_id_str.trim())) ? 10000 : Long.valueOf(role_id_str);
        List<Role> roleList = initService.getRole();//所有角色
        //获取传入角色对应的菜单，如果role_id没有传，则使用默认值10000
        Map<String , List<Menu>> map = this.peopleManageService.getRoleMenu(role_id);

        model.addAttribute("choose_role_id" , role_id);
        model.addAttribute("roleList" , roleList);
        model.addAttribute("top_menu_list" , map.get("top_menu_list"));
        model.addAttribute("sub_menu_list" , map.get("sub_menu_list"));
        return "/module/peoplemanage/rolemenu/index";
    }

    /**
     * 对角色-权限进行配置
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/rolemenu/doconfig" , method = RequestMethod.POST)
    @ResponseBody
    public int roleMenuDoConfig(Model model , HttpServletRequest request , HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        String sub_menu_ids = request.getParameter("sub_menu_id");//权限菜单id，xxx,xxx,xxx格式
        long role_id = Long.valueOf(request.getParameter("role_id"));//角色id

        return this.peopleManageService.roleMenuDoConfig(sub_menu_ids , role_id);
    }
}