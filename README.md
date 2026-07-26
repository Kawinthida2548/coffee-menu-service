# Coffee Menu Service — REST API
REST API เบื้องต้นสำหรับจัดการเมนูร้านกาแฟ (เพิ่ม / ดู / แก้ไข / ลบ) พัฒนาด้วย **Spring Boot** ตามหลักการแยกชั้น (Layered Design): **Controller → Service → Model**

---


| Layer | หน้าที่ |
|------|--------|
| Controller | รับ HTTP Request |
| Service | Business Logic |
| Model | โครงสร้างข้อมูล |

---

## Project Structure

```bash
coffee-menu-service/
├── src/main/java/com/example/coffee_menu_service/
│   ├── controller/
│   │   └── CoffeeController.java
│   ├── service/
│   │   └── CoffeeService.java
│   ├── model/
│   │   └── Coffee.java
│   └── CoffeeMenuServiceApplication.java
│
├── src/main/resources/
│   └── application.properties
│
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md
```
## วิธีติดตั้งและรันโปรเจกต์

**ขั้นตอนที่ 1: สร้างโปรเจกต์ที่ start.spring.io**
| ตัวเลือก | ค่าที่ใช้ |
|---|---|
| Project | Maven |
| Language | Java |
| Spring Boot | 4.1.0 |
| Group | `com.example` |
| Artifact | `coffee-menu-service` |
| Java version | 17 |
| Dependencies | Spring Web |

## สร้างโฟลเดอร์และไฟล์
```
สร้างโฟลเดอร์ `model`, `service`, `controller`
ภายใต้ `src/main/java/com/example/coffee_menu_service/`
(โฟลเดอร์เดียวกับที่มี `CoffeeMenuServiceApplication.java`)
```
### ขั้นตอนที่ 2: รันแอปพลิเคชัน
```bash
.\mvnw.cmd spring-boot:run
```
### ขั้นตอนที่ 3: . ตรวจสอบว่ารันสำเร็จ
จะได้ผลรันดังนี้
<img width="1052" height="332" alt="Screenshot 2569-07-24 at 15 39 20" src="https://github.com/user-attachments/assets/c21f2a0d-a71e-4404-82a9-8d3ddd339983" />

เมื่อเห็นข้อความนี้ แปลว่าแอปกำลังทำงานอยู่ที่ http://localhost:8080 และพร้อมรับ request แล้ว

## Endpoints ทั้งหมด

| # | Method | Path | คำอธิบาย | Request Body | Response สำเร็จ |
|---|--------|------|----------|---------------|------------------|
| 1 | GET | `/coffees` | ดูเมนูทั้งหมด | ไม่มี | `200 OK` |
| 2 | GET | `/coffees/{id}` | ดูเมนู 1 รายการตาม id | ไม่มี | `200 OK` (หรือ `404` ถ้าไม่เจอ) |
| 3 | POST | `/coffees` | เพิ่มเมนูใหม่ | JSON: `{name, price}` | `201 Created` |
| 4 | PUT | `/coffees/{id}` | แก้ไขเมนูเดิมตาม id | JSON: `{name, price}` | `200 OK` (หรือ `404` ถ้าไม่เจอ) |
| 5 | DELETE | `/coffees/{id}` | ลบเมนูตาม id | ไม่มี | `204 No Content` (หรือ `404` ถ้าไม่เจอ) |

## ตัวอย่างการเรียก API ผ่าน Postman

### 1. GET /coffees — ดูเมนูทั้งหมด
#### คำอธิบาย: ดึงรายการกาแฟทั้งหมดที่มีอยู่ในระบบ ไม่ต้องส่ง parameter หรือ body ใดๆ
#### Response ที่ได้: 200 OK
<img width="1470" height="956" alt="Screenshot 2569-07-26 at 15 52 22" src="https://github.com/user-attachments/assets/0d8e85de-c725-4637-a7c2-4e3560c61824" />

### 2. GET /coffees/{id} — ดูเมนูตาม id
#### คำอธิบาย: ดึงข้อมูลกาแฟเพียง 1 รายการ โดยระบุ id ต่อท้าย URL
#### Response ที่ได้: 200 OK
<img width="1470" height="956" alt="Screenshot 2569-07-26 at 15 52 43" src="https://github.com/user-attachments/assets/29b32d6c-4d4f-4fd5-8d91-af247d69e3fe" />

### 3. POST /coffees — เพิ่มเมนูใหม่
#### คำอธิบาย: สร้างเมนูกาแฟใหม่ ระบบจะสร้าง id ให้อัตโนมัติ
#### Response ที่ได้: 200 OK
<img width="1470" height="956" alt="Screenshot 2569-07-26 at 15 53 28" src="https://github.com/user-attachments/assets/f889e3db-42d1-4d05-bd7d-29eb8f03a8eb" />

#### เมื่อเพิ่มเมนูแล้ว จะได้เมนูเพิ่มเป็น 3 เมนู
<img width="1470" height="956" alt="Screenshot 2569-07-26 at 15 53 55" src="https://github.com/user-attachments/assets/3291a8ef-8514-4bf8-8b0f-0562e29b751e" />

### 4. PUT /coffees/{id} — แก้ไขเมนู
#### คำอธิบาย: แก้ไขข้อมูลกาแฟที่มีอยู่แล้ว โดยข้อมูลใหม่จะเข้าไปแทนที่ข้อมูลเดิม
#### Response ที่ได้: 200 OK
<img width="1470" height="956" alt="Screenshot 2569-07-26 at 15 54 44" src="https://github.com/user-attachments/assets/6eab0cfd-f0aa-4cfe-b1dd-67c63b116b6d" />

#### เมื่อแก้ไขเมนูแล้ว ราคาของ Latte จะเปลี่ยนเป็น 50
<img width="1470" height="956" alt="Screenshot 2569-07-26 at 15 55 50" src="https://github.com/user-attachments/assets/b31fde96-6bf3-42b2-a401-8d6977464537" />

### 5. DELETE /coffees/{id} — ลบเมนู
#### คำอธิบาย: ลบเมนูกาแฟออกจากระบบตาม id ที่ระบุ ไม่ต้องส่ง body
#### Response ที่ได้: 200 OK
<img width="1470" height="956" alt="Screenshot 2569-07-26 at 16 00 10" src="https://github.com/user-attachments/assets/d494ad87-a0dd-4e3e-9ae9-4dcf93b23616" />

#### เมนูที่มี id = 3 จะถูกลบและเหลือแค่ 2 เมนู

<img width="1470" height="956" alt="Screenshot 2569-07-26 at 16 00 18" src="https://github.com/user-attachments/assets/7ab4d8de-6d9b-465a-accf-34a61ba9819c" />

## โบนัส(+10) : คืน 404 Not Found เมื่อหา id ไม่เจอ
* วิธีทำงาน: ทุก endpoint ที่ต้องอ้างอิง id (GET /coffees/{id}, PUT /coffees/{id}, DELETE /coffees/{id}) ถูกเขียนให้เช็คก่อนว่ามี Coffee ที่ตรงกับ id นั้นอยู่ใน List จริงไหม โดยใช้ Optional<Coffee> จาก Service:
```bash
@GetMapping("/{id}")
public ResponseEntity<Coffee> getById(@PathVariable int id) {
    return coffeeService.getById(id)
            .map(ResponseEntity::ok)                 // เจอ → 200 OK
            .orElse(ResponseEntity.notFound().build()); // ไม่เจอ → 404 Not Found
}
```
<img width="1470" height="956" alt="Screenshot 2569-07-26 at 16 02 47" src="https://github.com/user-attachments/assets/c5b5a872-983a-47a2-833c-bab5af264f8e" />

---
## คำตอบ Discussion 

### 1. HTTP method แต่ละตัว (GET/POST/PUT/DELETE) ต่างกันอย่างไร

GET ใช้ดูข้อมูล ไม่เปลี่ยนแปลงอะไร (เช่น `GET /coffees` ดูเมนูทั้งหมด) POST ใช้สร้างข้อมูลใหม่ (เช่น `POST /coffees` เพิ่ม Cappuccino) PUT ใช้แก้ไขข้อมูลเดิมทั้งก้อน (เช่น `PUT /coffees/2` เปลี่ยนราคา Latte) ส่วน DELETE ใช้ลบข้อมูลออกจากระบบ (เช่น `DELETE /coffees/3`)

### 2. ทำไมต้องแยก Controller กับ Service ออกจากกัน มีข้อดีอย่างไรถ้าโปรแกรมโตขึ้น

Controller มีหน้าที่รับ request/ส่ง response อย่างเดียว ส่วน Service เก็บ logic และข้อมูลจริง การแยกแบบนี้ทำให้แก้ไขง่าย เช่น ถ้าจะเปลี่ยนจากเก็บใน List เป็นฐานข้อมูล แก้แค่ Service โดยไม่กระทบ Controller เลย และยังทดสอบ logic แยกจาก HTTP ได้ด้วย

### 2. ข้อมูลที่เก็บไว้ใน List ใน memory หายไปตอนไหน และถ้าอยากให้ไม่หายควรทำอย่างไร

ข้อมูลอยู่ใน RAM เท่านั้น ไม่ได้เขียนลงดิสก์ จึงหายทันทีที่แอป restart หรือหยุดทำงาน ถ้าอยากให้ไม่หายต้องเปลี่ยนไปเก็บในฐานข้อมูล เช่น H2 หรือ MySQL ร่วมกับ Spring Data JPA แทน

### 2. @RestController, @GetMapping, @PostMapping, @PathVariable, @RequestBody แต่ละตัวทำหน้าที่อะไร

`@RestController` บอกว่า class นี้รับ HTTP request และตอบกลับเป็น JSON `@GetMapping`/`@PostMapping` กำหนดว่า method ไหนรับ GET/POST ที่ path ไหน `@PathVariable` ดึงค่าจาก URL เช่น `{id}` มาเป็นตัวแปร ส่วน `@RequestBody` แปลง JSON ที่ส่งมาใน body ให้เป็น Java object อัตโนมัติ

