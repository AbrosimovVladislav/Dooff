import {get} from "@/service/rest-client";
import {IDish} from "@/constants/interface";

export async function getDishes(): Promise<IDish[]> {
  const url = "http://localhost:8080/api/v1/basic";
  const response = await get(url);
  return response;
}