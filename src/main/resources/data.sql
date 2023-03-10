/*Sample Data for the vehicle_line table for testing search */

insert into vehicle_line(id, name, text_description) values
(1, 'Cars','Attention car enthusiasts: Make your wildest car ownership dreams come true. Whether you are looking for classic muscle cars, dream sports cars or movie-inspired miniatures, you will find great choices in this category. These replicas feature superb attention to detail and craftsmanship and offer features such as working steering system, opening forward compartment, opening rear trunk with removable spare wheel, 4-wheel independent spring suspension, and so on. The models range in size from 1:10 to 1:24 scale and include numerous limited edition and several out-of-production vehicles. All models include a certificate of authenticity from their manufacturers and come fully assembled and ready for display in the home or office.'),
(2, 'Motorcycles','Our motorcycles are state of the art replicas of classic as well as contemporary motorcycle legends such as Harley Davidson, Ducati and Vespa. Models contain stunning details such as official logos, rotating wheels, working kickstand, front suspension, gear-shift lever, footbrake lever, and drive chain. Materials used include diecast and plastic. The models range in size from 1:10 to 1:50 scale and include numerous limited edition and several out-of-production vehicles. All models come fully assembled and ready for display in the home or office. Most include a certificate of authenticity.'),
(3, 'Planes','Unique, diecast airplane and helicopter replicas suitable for collections, as well as home, office or classroom decorations. Models contain stunning details such as official logos and insignias, rotating jet engines and propellers, retractable wheels, and so on. Most come fully assembled and with a certificate of authenticity from their manufacturers.'),
(4, 'Ships','The perfect holiday or anniversary gift for executives, clients, friends, and family. These handcrafted model ships are unique, stunning works of art that will be treasured for generations! They come fully assembled and ready for display in the home or office. We guarantee the highest quality, and best value.'),
(5, 'Trains','Model trains are a rewarding hobby for enthusiasts of all ages. Whether you\'re looking for collectible wooden trains, electric streetcars or locomotives, you\'ll find a number of great choices for any budget within this category. The interactive aspect of trains makes toy trains perfect for young children. The wooden train sets are ideal for children under the age of 5.'),
(6, 'Trucks and Buses','The Truck and Bus models are realistic replicas of buses and specialized trucks produced from the early 1920s to present. The models range in size from 1:12 to 1:50 scale and include numerous limited edition and several out-of-production vehicles. Materials used include tin, diecast and plastic. All models include a certificate of authenticity from their manufacturers and are a perfect ornament for the home and office.');

/*Sample data for the vehicle_product table for testing search - breaks normalisation to ease local testing */

insert into vehicle_product (id, name, vehicle_line_id, manufacturer, price, currency) values
(1, 'Mercedes-Benz GLC', 1, 'Mercedes-Benz', 45000, "GBP"),
(2, 'Mercedes-Benz A-Class', 1, 'Mercedes-Benz', 30800, "GBP"),
(3, 'Mercedes-Benz C-Class', 1, 'Mercedes-Benz', 43060, "GBP"),
(4, 'Mercedes-Benz CLS', 1, 'Mercedes-Benz', 70000, "EUR"),
(5, 'Mercedes-Benz GLB', 1, 'Mercedes-Benz', 40000, "EUR"),
(6, 'BMW X5', 1, 'BMW', 61000, "USD"),
(7, 'BMW iX3', 1, 'BMW', 70000, "EUR"),
(8, 'Cessna 120', 3, 'Cessna', 30000, "GBP"),
(9, 'Cessna 170', 3, 'Cessna', 82000, "EUR"),
(10, 'Cessna 162 Skycatcher', 3, 'Cessna', 5000000, "USD"),
(11, 'Cessna Citation M2', 3, 'Cessna', 1200000, "USD"),
(12, 'Boeing 787', 3, 'Boeing', 300500000, "USD"),
(13, 'Airbus A380', 3, 'Airbus', 500000000, "USD");