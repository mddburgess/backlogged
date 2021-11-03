import {createApi, fetchBaseQuery} from "@reduxjs/toolkit/query/react";
import {BacklogItem} from "types/BacklogItem";

const API = createApi({
    reducerPath: "API",
    baseQuery: fetchBaseQuery({baseUrl: "/api"}),
    tagTypes: ["backlog"],
    endpoints: (builder) => ({
        listBacklog: builder.query<BacklogItem[], void>({
            query: () => "backlog",
            providesTags: (result) => [
                {type: "backlog", id: "list"},
                ...(result?.map(({id}) => ({type: "backlog" as const, id})) ?? [])
            ]
        }),
        retrieveBacklogItem: builder.query<BacklogItem, number>({
            query: (id) => `backlog/${id}`,
            providesTags: (result, error, id) => [
                {type: "backlog", id}
            ]
        }),
        createBacklogItem: builder.mutation<BacklogItem, Partial<BacklogItem>>({
            query: (backlogItem) => ({
                url: "backlog",
                method: "POST",
                body: backlogItem
            }),
            invalidatesTags: [{type: "backlog", id: "list"}]
        }),
        updateBacklogItem: builder.mutation<BacklogItem, BacklogItem>({
            query: (backlogItem) => ({
                url: `backlog/${backlogItem.id}`,
                method: "PUT",
                body: backlogItem
            }),
            invalidatesTags: (result, error, {id}) => [{type: "backlog", id}]
        }),
        deleteBacklogItem: builder.mutation<void, BacklogItem>({
            query: (backlogItem) => ({
                url: `backlog/${backlogItem.id}`,
                method: "DELETE"
            }),
            invalidatesTags: (result, error, {id}) => [{type: "backlog", id}]
        })
    })
})

export default API;
