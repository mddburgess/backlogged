import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";
import {BacklogItem} from "types/BacklogItem";

const API = createApi({
    reducerPath: "API",
    baseQuery: fetchBaseQuery({baseUrl: "api"}),
    tagTypes: ["backlog"],
    endpoints: (builder) => ({
        listBacklog: builder.query<BacklogItem[], void>({
            query: () => "backlog",
            providesTags: (result) => [
                {type: "backlog", id: "list"},
                ...(result?.map(({id}) => ({type: "backlog" as const, id})) ?? [])
            ]
        }),
        createBacklogItem: builder.mutation<BacklogItem, BacklogItem>({
            query: (backlogItem) => ({
                url: "backlog",
                method: "POST",
                body: backlogItem
            }),
            invalidatesTags: [{type: "backlog", id: "list"}]
        }),
        deleteBacklogItem: builder.mutation<void, number>({
            query: (id) => ({
                url: `backlog/${id}`,
                method: "DELETE"
            }),
            invalidatesTags: (result, error, id) => [{type: "backlog", id}]
        })
    })
})

export default API;
