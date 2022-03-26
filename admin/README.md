1. **权限控制**
   `Role-Based Access Control` 基于角色的访问控制

   简单地说，一个用户拥有若干角色，每一个角色拥有若干个菜单，

   菜单中存在菜单权限与按钮权限， 这样，就构造成“用户-角色-菜单” 的授权模型。

   在这种模型中，用户与角色、角色与菜单之间构成了多对多的关系

2. **数据交互**
   安全框架使用的是 `Spring Security + Jwt Token`，

   访问后端接口需在请求头中携带`token`进行访问

   用户登录 -> 后端验证登录返回 `token` -> 前端带上`token`请求后端数据 -> 后端返回数据

3. **权限注解**

   `Spring Security` 提供了`Spring EL`表达式，`@preAuthorize`，

   允许我们在定义接口访问的方法上面添加注解，来控制访问权限，

   加入了自定义权限验证方式，在验证的时候默认给拥有admin权限的用户放行

   `ElPermissionConfig` 配置类，`@PreAuthorize("@el.check('user:list','user:add')") `

4. **接口放行**

   在我们使用的时候，有些接口是不需要验证权限的，这个时候就需要我们给接口放行，
   
   `@AnonymousAccess` + 修改配置文件方式，
   
   使用 `permitAll()` 方法所有人都能访问，包括带上 `token` 访问
   
   使用 `anonymous()` 所有人都能访问，但是带上 `token` 访问后会报错

