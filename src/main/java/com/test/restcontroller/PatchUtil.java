package com.test.restcontroller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.test.dto.TaskDTO;
import com.test.entity.tasks.UserTask;

public class PatchUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static TaskDTO applyPatchToCustomer(JsonPatch patch, TaskDTO userTask) throws JsonPatchException, JsonProcessingException {
        objectMapper.registerModule(new JSR310Module());
        JsonNode patched = patch.apply(objectMapper.convertValue(userTask, JsonNode.class));
        return objectMapper.treeToValue(patched, TaskDTO.class);
    }


}
