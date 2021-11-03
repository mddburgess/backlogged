export interface BacklogItem {
    id: number,
    name: string,
    type: string,
    status: string,
    activityTime?: string,
    activities?: Activity[]
}

export type Activity = StatusActivity | TimeActivity;

export interface StatusActivity {
    type: "STATUS",
    id: number,
    activityDate: string,
    fromStatus: string,
    toStatus: string
}

export interface TimeActivity {
    type: "TIME",
    id: number,
    activityDate: string,
    duration: string
}
