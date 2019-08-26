在elasticsearch中的专用名词：索引、类型、文档

这里的索引是指存放数据的地方，类似数据库
类型用来定义数据结构，类似表
文档就是数据，可以认为一个文档就是一条记录

快速入门：
1、先建立一个索引poems
2、建立一个名叫poem的类型，类型通过Mapping定义每个字段的类型

Keyword、Text、Integer

keyword类型不分词，直接根据字符串内容建立反向索引；Text 类型在存入 Elasticsearch 的时候，会先分词，然后根据分词后的内容建立反向索引。
