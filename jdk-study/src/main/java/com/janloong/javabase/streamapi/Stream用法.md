
### 函数式接口

|函数接口|抽象方法|功能|参数|返回类型|
|:---:|:---:|:---:|:---:|:---:|
|Predicate|test(T t)|判断真假|T|boolean|
|Consumer|accept(T t)|消费消息|T|void|
|Function|R apply(T t)|将T映射为R（转换功能）|T|R|
|Supplier|T get()|生产消息|空|T|
|UnaryOperator|T apply(T t)|一元操作|T|T|
|BinaryOperator|apply(T t, U u)|二元操作|(T,T)|T|





### Stream jsonarray foreach 高级用法

    private static final String COMMITS_IN_PATCH_IDENTIFIER = "patchInformation_svnRevisionpublic";  //key used to identify the commits in a patch from JSON response received from PMT
    private static final String KEY_STRING = "name";
    private static final String VALUE_STRING = "value";

    public List<String> getCommitIds (JSONArray array) {
         return arrayToStream(array)
                .map(JSONObject.class::cast)
                .filter(o -> o.get(KEY_STRING).equals(COMMITS_IN_PATCH_IDENTIFIER))
                .findFirst()
                .map(o -> (JSONArray) o.get(VALUE_STRING))
                .map(Main::arrayToStream)
                .map(commits ->
                        commits.map(Object::toString)
                                .map(String::trim)
                                .collect(Collectors.toList())
                )
                .orElseGet(Collections::emptyList);
    }

    @Nonnull
    private static Stream<Object> arrayToStream(JSONArray array) {
        return StreamSupport.stream(array.spliterator(), false);
    }