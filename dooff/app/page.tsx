'use client'

import {TableReviews} from "@/components/TableReviews";
import {useEffect, useState} from "react";
import {getDishes} from "@/service/dishes-service";
import {IDish} from "@/constants/interface";

export default function Home() {

  const [data, setData] = useState<IDish[]>([]);

  useEffect( () => {
    const fetchData = async () => {
      try {
        const response = await getDishes();
        console.log(response)
        setData(response);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  },[]);

  return (
      <div className="p-3 my-3 mx-5">
        <TableReviews data={data}/>
      </div>
  )
}

//  data: {
//     title: string;
//     author: string;
//     year: number;
//     reviews: { positive: number; negative: number };
//   }[];
