
## 登陆注册
### 认证表单
#### 请求表单
| 键 | 类型 | 取值 | 意义 |
| -- | --- | --- | --- |
| type | 字符串 | login/register | 请求类型，分别为注册和登陆 |
| login_type | 字符串 | passwd/examine | 登陆类型，只有在type为login时有此键 |
| phone_number | 字符串 | 11位数字 | 手机号码 |
| passwd | 字符串 | 非空 | 注册或登陆时的密码，在注册或者密码登陆时有此键 |
| examine_code | 字符串 | 6位字符串 | 注册或者通过验证码登陆时有此键 | 

#### 回复表单  
| 键 | 类型 | 取值 | 意义 |
| -- | -- | --| -- |
| state | String | Y/N/E | 认证状态，E代表服务器或其他未知错误 |
| error_message | String | / | 错误信息，只有在state为E时有此键 |

### 获取验证码
#### 请求表单  
| 键 | 类型 | 取值 | 意义 |
| -- | -- | --| -- |  
| phone_number | String | 11位数字 | 手机号码 |

#### 回复表单
| 键 | 类型 | 取值 | 意义 |
| -- | -- | --| -- |  
| state | string | Y/N/E | 请求状态，E代表服务器或其他未知错误 |  
| error_message | String | / | 错误信息，只有在state为E时有此键 |
### 登陆注册整体请求流程  
- 登陆 
  - 密码登陆
    - 直接提交登陆表单
    - 获取状态
  - 验证码登陆
    - 首先提交请求验证码表单
    - 获取发送验证码状态
      - 成功
        - 提交登陆表单
      - 失败
        - 错误处理  
- 注册
  - 类比登陆

## 筛查清单 
### 获取体检表单内容  
#### 请求表单
| 键 | 类型 | 取值 | 意义 |
| -- | -- | --| -- |  
| phone_number | String | / | 手机号码 |
| system | String | / |请求的身体系统名称 |
#### 回复表单  
| 键 | 类型 | 取值 | 意义 |
| -- | -- | --| -- |  
| id | String | / | 体检表单的id |
| content | String | / | 问卷表单的问题内容 |
| state | String | Y/E | 请求状态，出现错误为E，正常为Y |  
| error_message | String | / | 错误信息，只有在state为E时有此键 |
### 提交表单获取得分
#### 请求表单  
| 键 | 类型 | 取值 | 意义 |
| -- | -- | --| -- |  
| phone_number | String | / | 手机号码 |
| id | String | / | 体检表单id |
| answer | String | 由一系列数字组成的列表 | 代表用户对不同的问题选项的选择列表 |
#### 回复表单  
| 键 | 类型 | 取值 | 意义 |
| -- | -- | -- | --|  
| id | String | 一串数字 | 体检报告的清单的提交id |
| score | 字符串 | 一个浮点数字符串，取值范围1-10 | 提交报告的分数 |
| suggestion | 字符串 | 文本 | 一些建议 |
| state | String | Y/E | 请求状态，出现错误为E，正常为Y |  
| error_message | String | / | 错误信息，只有在state为E时有此键 |

### 整体流程(以消化系统为例)
- 用户请求消化系统的体检表单内容  
- 服务器返回对应消化系统问卷清单的内容以及此清单id
- 用户填写后发送填写结果到服务器
- 服务器处理后返回分数  


```mysql
CREATE TABLE questionnaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    time TIMESTAMP,
    source VARCHAR(255)
);

CREATE TABLE question(
  id INT AUTO_INCREMENT PRIMARY KEY,
  questionnaire_id INT,
  question TEXT,
  type VARCHAR(255),
  candidate_answers TEXT,
  answer VARCHAR(255),
  FOREIGN KEY (questionnaore_id REFERENCES questionnaire(id))
)

CREATE TABLE user_answer(
  id INT AUTO_INCREMENT PRIMARY KEY,
  questionnaire
)
```
