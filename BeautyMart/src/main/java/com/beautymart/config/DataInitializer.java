package com.beautymart.config;

import com.beautymart.entity.Product;
import com.beautymart.entity.User;
import com.beautymart.repository.ProductRepository;
import com.beautymart.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.math.BigDecimal;

@Configuration
public class DataInitializer {

 @Bean
 BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

 @Bean
 CommandLineRunner seed(UserRepository users, ProductRepository products, BCryptPasswordEncoder enc){
 return args -> {
 if(users.count()==0){
 users.save(new User("Beauty Admin","admin@beautymart.com",enc.encode("admin123"),"ADMIN"));
 users.save(new User("Demo Buyer","buyer@beautymart.com",enc.encode("buyer123"),"BUYER"));
 users.save(new User("Demo Seller","seller@beautymart.com",enc.encode("seller123"),"SELLER"));
 }
 User demoSeller = users.findByEmail("seller@beautymart.com").orElse(null);
 if(products.count()==0){
 String[][] p = {
 {"Lakme Absolute Foundation","Lakme","Makeup","699","899","Beige","product1.jpg"},
 {"Lakme 9to5 Lip Color","Lakme","Makeup","449","599","Rose","product2.jpg"},
 {"Lakme Eyeconic Kajal","Lakme","Eye Makeup","199","225","Black","product3.jpg"},
 {"Lakme Sun Expert SPF 50","Lakme","Skincare","399","475","White","product4.jpg"},
 {"Maybelline Fit Me Foundation","Maybelline","Makeup","599","799","Natural Beige","product5.jpg"},
 {"Maybelline Super Stay Lipstick","Maybelline","Makeup","549","699","Red","product6.jpg"},
 {"Maybelline Colossal Kajal","Maybelline","Eye Makeup","199","249","Black","product7.jpg"},
 {"Maybelline Lash Sensational Mascara","Maybelline","Eye Makeup","499","599","Black","product8.jpg"},
 {"Maybelline Baby Lips","Maybelline","Lip Care","149","175","Cherry","product9.jpg"},
 {"Maybelline Cheek Heat Blush","Maybelline","Makeup","399","499","Coral","product10.jpg"},
 {"Sugar Matte As Hell Crayon","SUGAR","Makeup","699","799","Berry","product11.jpg"},
 {"Sugar Smudge Me Not Lipstick","SUGAR","Makeup","599","699","Wine","product12.jpg"},
 {"Sugar Wingman Eyeliner","SUGAR","Eye Makeup","499","599","Black","product13.jpg"},
 {"Sugar Contour De Force","SUGAR","Makeup","699","899","Cocoa","product14.jpg"},
 {"Sugar Bling Leader Highlighter","SUGAR","Makeup","649","799","Champagne","product15.jpg"},
 {"Mamaearth Ubtan Face Wash","Mamaearth","Skincare","249","299","Yellow","product16.jpg"},
 {"Mamaearth Vitamin C Serum","Mamaearth","Skincare","499","599","Clear","product17.jpg"},
 {"Mamaearth Aloe Vera Gel","Mamaearth","Skincare","199","249","Clear","product18.jpg"},
 {"Mamaearth Lip Balm","Mamaearth","Lip Care","149","199","Pink","product19.jpg"},
 {"Mamaearth Rice Face Wash","Mamaearth","Skincare","299","349","White","product20.jpg"},
 {"Cetaphil Gentle Cleanser","Cetaphil","Skincare","399","499","White","product21.jpg"},
 {"Cetaphil Moisturising Lotion","Cetaphil","Skincare","499","599","White","product22.jpg"},
 {"Cetaphil Sunscreen SPF 50","Cetaphil","Skincare","799","899","White","product23.jpg"},
 {"Garnier Vitamin C Serum","Garnier","Skincare","449","599","Clear","product24.jpg"},
 {"Garnier Face Wash","Garnier","Skincare","199","249","White","product25.jpg"},
 {"Garnier Micellar Water","Garnier","Skincare","299","349","Clear","product26.jpg"},
 {"Nivea Soft Cream","Nivea","Skincare","249","299","White","product27.jpg"},
 {"Nivea Cherry Shine Lip Balm","Nivea","Lip Care","169","199","Cherry","product28.jpg"},
 {"Nivea Body Lotion","Nivea","Body Care","299","349","White","product29.jpg"},
 {"Dove Hair Therapy Shampoo","Dove","Hair Care","399","499","White","product30.jpg"},
 {"Dove Intense Repair Conditioner","Dove","Hair Care","349","425","White","product31.jpg"},
 {"Tresemme Keratin Shampoo","Tresemme","Hair Care","449","549","Black","product32.jpg"},
 {"Tresemme Conditioner","Tresemme","Hair Care","399","499","White","product33.jpg"},
 {"L'Oreal Paris Revitalift Serum","L'Oreal","Skincare","799","999","Clear","product34.jpg"},
 {"L'Oreal Paris Infallible Foundation","L'Oreal","Makeup","899","1099","Golden Beige","product35.jpg"},
 {"L'Oreal Paris Voluminous Mascara","L'Oreal","Eye Makeup","699","799","Black","product36.jpg"},
 {"Biotique Morning Nectar Face Wash","Biotique","Skincare","199","249","Green","product37.jpg"},
 {"Biotique Bio Morning Nectar Lotion","Biotique","Skincare","299","349","White","product38.jpg"},
 {"Plum Green Tea Face Wash","Plum","Skincare","399","475","Green","product39.jpg"},
 {"Plum 15% Vitamin C Serum","Plum","Skincare","699","799","Clear","product40.jpg"},
 {"Plum Candy Melts Lip Balm","Plum","Lip Care","295","325","Pink","product41.jpg"},
 {"Colorbar Perfect Match Primer","Colorbar","Makeup","649","799","Clear","product42.jpg"},
 {"Colorbar Velvet Matte Lipstick","Colorbar","Makeup","499","599","Nude","product43.jpg"},
 {"Colorbar Waterproof Eyeliner","Colorbar","Eye Makeup","349","399","Black","product44.jpg"},
 {"Faces Canada Ultime Pro Mascara","Faces Canada","Eye Makeup","499","599","Black","product45.jpg"},
 {"Faces Canada Comfy Matte Lipstick","Faces Canada","Makeup","449","549","Mauve","product46.jpg"},
 {"The Face Shop Rice Water Cleanser","The Face Shop","Skincare","599","699","White","product47.jpg"},
 {"The Face Shop Aloe Soothing Gel","The Face Shop","Skincare","399","499","Clear","product48.jpg"},
 {"WOW Skin Science Face Serum","WOW","Skincare","499","599","Clear","product49.jpg"},
 {"WOW Apple Cider Vinegar Shampoo","WOW","Hair Care","399","499","White","product50.jpg"},
 {"Himalaya Nourishing Skin Cream","Himalaya","Skincare","149","199","White","product51.jpg"}
 };
 for(String[] x:p){
 BigDecimal price=new BigDecimal(x[3]), mrp=new BigDecimal(x[4]);
 String img = "/images/products/" + x[6];
 Product prod = new Product(x[0],x[1],x[2],price,mrp,x[5],
 "Premium "+x[2].toLowerCase()+" product from "+x[1]+". Suitable for everyday beauty and personal care.",
 img, 20 + (int)(Math.random()*80), 4.0 + Math.round(Math.random()*10)/10.0);
 prod.setSeller(demoSeller);
 products.save(prod);
 }
 }
 };
 }
}