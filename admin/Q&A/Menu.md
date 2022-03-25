## Menu

### Prefix

In the admin project, the process of searching and building menu is important in my opinion.

Through this, you can get the specific process of JPA and enhance understanding of the entire delivery.

Expect the JPA, you also can learn how to build menu from database and pass it to front-end rendering.

In the following content, I will try my best to record the whole process.

-----

### Front End Send Request

After login successfully, front end will `loadMenus`:

 <img src="https://raw.githubusercontent.com/Eminem-x/SpringBoot/main/admin/Q%26A/pic/menu/loadMenu.png" alt="system call" style="max-width: 100%; zoom: 80%;">

and `loadMenus` contains the `buildMenus` request:

 <img src="https://raw.githubusercontent.com/Eminem-x/SpringBoot/main/admin/Q%26A/pic/menu/buildMenu.png" alt="system call" style="max-width: 100%;">

----

### Back End Receive And Process Request

 <img src="https://raw.githubusercontent.com/Eminem-x/SpringBoot/main/admin/Q%26A/pic/menu/receiveRequest.png" alt="system call" style="max-width: 100%; zoom: 80%;">

It will return the result in three steps.

* Find the specific user who may have different roles (**<a href="">RABC</a>**)
* Find menus and submenus from database (**JPA**)
* Convert DTO to VO and then deliver to front end