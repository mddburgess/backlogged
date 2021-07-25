import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";
import {BacklogItem} from "types/BacklogItem";

const API = createApi({
    reducerPath: "API",
    baseQuery: fetchBaseQuery({baseUrl: "api"}),
    endpoints: (builder) => ({
        listBacklog: builder.query<BacklogItem[], void>({
            query: () => "backlog"
        })
    })
})

export default API;
