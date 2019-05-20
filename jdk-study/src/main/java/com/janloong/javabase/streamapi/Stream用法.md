
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