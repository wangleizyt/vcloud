//package cn.com.vcloud.test.config;
//
//import javax.annotation.Resource;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import cn.com.vcloud.admin.core.jwt.JwtAuthenticationEntryPoint;
//import cn.com.vcloud.admin.core.jwt.JwtAuthenticationFilter;
//import cn.com.vcloud.admin.service.impl.UserDetailsServiceImpl;
//
///**
// * 安全设置
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Resource
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//    @Resource
//    private JwtAuthenticationFilter jwtAuthenticationFilter;
//
//    @Bean
//    @Override
//    public UserDetailsServiceImpl userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    protected void configure(final AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth
//                // 自定义获取用户信息
//                .userDetailsService(this.userDetailsService())
//                // 设置密码加密
//                .passwordEncoder(this.passwordEncoder());
//    }
//
//    @Override
//    protected void configure(final HttpSecurity http)
//            throws Exception {
//    	
//        http    // 关闭cors
//                .cors().disable()
//                // 关闭csrf
//                .csrf().disable()
//                // 无状态Session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                // 异常处理
//                .exceptionHandling().authenticationEntryPoint(this.jwtAuthenticationEntryPoint).and()
//                // 对所有的请求都做权限校验
//                .authorizeRequests()
//                // 允许登录和注册
//                .antMatchers(
//                        HttpMethod.POST,
//                        "/v1/login"
//                ).permitAll()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/v1",
//                        "/druid"
//                ).permitAll()
//                .antMatchers(
//                        HttpMethod.GET,
//                        "/favicon.ico",
//                        "/v2/api-docs", 
//                        "/configuration/ui",
//                        "/swagger-resources/**",
//                        "/configuration/security",
//                        "/swagger-ui.html",
//                        "/webjars/**",
//                        "/swagger-resources/configuration/ui",
//                        "/swagge‌​r-ui.html"
//                ).permitAll()
//                // 除上面外的所有请求全部需要鉴权认证
//                .anyRequest().authenticated().and();
//
//        http    // 基于定制JWT安全过滤器
//                .addFilterBefore(this.jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//        // 禁用页面缓存
//        http.headers().cacheControl();
//    }
//}